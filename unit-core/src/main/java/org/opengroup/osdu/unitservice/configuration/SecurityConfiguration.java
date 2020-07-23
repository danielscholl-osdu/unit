package org.opengroup.osdu.unitservice.configuration;

import org.opengroup.osdu.unitservice.middleware.AuthenticationRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected static final String[] ALLOWED_URLS = {
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

    private final String entUrl;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public SecurityConfiguration(@Value("${osdu.entitlement.url}") String entUrl,
                                 HandlerExceptionResolver handlerExceptionResolver) {
        this.entUrl = entUrl;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationRequestFilter authhenticationFilter = new AuthenticationRequestFilter(entUrl, handlerExceptionResolver);
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(authhenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(ALLOWED_URLS);
        super.init(web);
    }
}
