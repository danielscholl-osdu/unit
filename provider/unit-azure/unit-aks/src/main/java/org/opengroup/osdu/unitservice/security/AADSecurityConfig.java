package org.opengroup.osdu.unitservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.spring.autoconfigure.aad.AADAppRoleStatelessAuthenticationFilter;
import org.opengroup.osdu.unitservice.util.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AADSecurityConfig extends WebSecurityConfigurerAdapter implements AccessDeniedHandler, AuthenticationEntryPoint {

    @Autowired
    private AADAppRoleStatelessAuthenticationFilter appRoleAuthFilter;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String[] AUTH_WHITELIST = {
            "/",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/_ah/**",
            "/error",
            "/favicon.ico",
            "/csrf",
            "/api/unit",
            "/api/unit/v2/api-docs",
            "/api/unit/swagger-resources/**",
            "/api/unit/swagger-ui.html",
            "/api/unit/webjars/**",
            "/api/unit/_ah/**",
            "/api/unit/error",
            "/api/unit/favicon.ico",
            "/api/unit/csrf" // Required to prevent errors in logs while Swagger is trying to discover a valid csrf token. Should be deleted after the issue on the Swagger's side https://github.com/springfox/springfox/issues/2578 is resolved
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(appRoleAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        writeUnauthorizedError(httpServletResponse);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        writeUnauthorizedError(response);
    }

    private static void writeUnauthorizedError(HttpServletResponse response) throws IOException {
        AppError appError = AppError.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("The user is not authorized to perform this action")
                .reason("Unauthorized")
                .build();
        String body = OBJECT_MAPPER.writeValueAsString(appError);

        PrintWriter out = response.getWriter();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(body);
        out.flush();
    }
}
