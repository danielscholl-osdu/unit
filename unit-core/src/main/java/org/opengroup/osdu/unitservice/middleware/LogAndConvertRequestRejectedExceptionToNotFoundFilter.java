package org.opengroup.osdu.unitservice.middleware;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHeaders;
import org.opengroup.osdu.core.common.logging.JaxRsDpsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogAndConvertRequestRejectedExceptionToNotFoundFilter extends GenericFilterBean {

    @Autowired
    private JaxRsDpsLog log;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (RequestRejectedException e) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;
            log.error(String.format("request_rejected: remote=%s, user_agent=%s, request_url=%s",
                request.getRemoteHost(), request.getHeader(HttpHeaders.USER_AGENT), request.getRequestURL()), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
