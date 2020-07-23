package org.opengroup.osdu.unitservice.security;

import org.opengroup.osdu.unitservice.configuration.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.HandlerExceptionResolver;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends SecurityConfiguration {
	
    public SecurityConfig(@Value("${osdu.entitlement.url}") String entUrl, HandlerExceptionResolver handlerExceptionResolver) {
		super(entUrl, handlerExceptionResolver);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        //.sessionManagement().sessionCreationPolicy(STATELESS)
        //.and()
        .authorizeRequests()
        //.antMatchers(ALLOWED_URLS).permitAll()
        .antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .oauth2ResourceServer().jwt();        
    }
}
