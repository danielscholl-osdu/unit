// Copyright © 2020 Amazon Web Services
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.opengroup.osdu.unitservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.opengroup.osdu.unitservice.middleware.AuthenticationRequestFilter;
import org.opengroup.osdu.unitservice.middleware.AuthenticationService;
import org.opengroup.osdu.unitservice.util.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthSecurityConfig extends WebSecurityConfigurerAdapter implements AccessDeniedHandler, AuthenticationEntryPoint {
      
    private AuthenticationRequestFilter authFilter;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String[] AUTH_WHITELIST = {
            "/",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/info",
            "/webjars/**",
            "/_ah/**",
            "/actuator/**",
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
            "/api/unit/actuator",
            "/api/unit/actuator/**",
            "/api/unit/actuator/health",
            "/api/unit/csrf" // Required to prevent errors in logs while Swagger is trying to discover a valid csrf token. Should be deleted after the issue on the Swagger's side https://github.com/springfox/springfox/issues/2578 is resolved
    };

    //AuthenticationRequestFilter is not a recognized bean, so construct it manually
    public AuthSecurityConfig(AuthenticationService authenticationService) {
        authFilter = new AuthenticationRequestFilter(authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
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
