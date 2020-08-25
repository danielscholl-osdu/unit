package org.opengroup.osdu.unitservice.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {

		
		//AuthenticationRequestFilter authhenticationFilter = new AuthenticationRequestFilter( "" , handlerExceptionResolver);
		
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
