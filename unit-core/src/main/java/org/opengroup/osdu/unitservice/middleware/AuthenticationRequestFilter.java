package org.opengroup.osdu.unitservice.middleware;


import org.opengroup.osdu.core.common.entitlements.EntitlementsAPIConfig;
import org.opengroup.osdu.core.common.entitlements.EntitlementsFactory;
import org.opengroup.osdu.core.common.entitlements.IEntitlementsFactory;
import org.opengroup.osdu.core.common.entitlements.IEntitlementsService;
import org.opengroup.osdu.core.common.model.entitlements.EntitlementsException;
import org.opengroup.osdu.core.common.model.entitlements.Groups;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import org.opengroup.osdu.unitservice.util.AppException;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

public class AuthenticationRequestFilter extends OncePerRequestFilter {

    private static Logger logger = Logger.getLogger(AuthenticationRequestFilter.class.getName());

    private final String entitlementsUrl;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public AuthenticationRequestFilter(String entitlementsUrl,
                                       HandlerExceptionResolver handlerExceptionResolver) {
        this.entitlementsUrl = entitlementsUrl;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        MultiValueMap<String, String> requestHeaders = httpHeaders(httpServletRequest);
        DpsHeaders dpsHeaders = DpsHeaders.createFromEntrySet(requestHeaders.entrySet());
        dpsHeaders.addCorrelationIdIfMissing();
        IEntitlementsFactory factory = getEntitlementsFactory();
        IEntitlementsService service = factory.create(dpsHeaders);
        try {
            Groups groups = service.getGroups();
            String message = String.format("User authenticated | User: %s", groups.getMemberEmail());
            authenticate(groups);
            logger.info(message);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (EntitlementsException e) {
            String message = String.format("User not authenticated. Response: %s", e.getHttpResponse());
            logger.warning(message);
            AppException unauthorized = AppException.createUnauthorized("Error: " + e.getMessage());
            handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, unauthorized);
        }
        catch (NullPointerException e) {
            String message = String.format("User not authenticated. Null pointer exception: %s", e.getMessage());
            logger.warning(message);
            AppException unauthorized = AppException.createUnauthorized("Error: " + e.getMessage());
            handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, unauthorized);
        }

    }

    private void authenticate(Groups groups) {
        OsduAuthentication authentication = new OsduAuthentication(groups, emptyList());
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private HttpHeaders httpHeaders(@NonNull HttpServletRequest httpRequest) {
        return Collections
                .list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpRequest.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
    }

    private IEntitlementsFactory getEntitlementsFactory() {
        return new EntitlementsFactory(EntitlementsAPIConfig.builder().rootUrl(entitlementsUrl).build());
    }
}
