/*
  Copyright 2020 Google LLC
  Copyright 2020 EPAM Systems, Inc

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.opengroup.osdu.unitservice.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.opengroup.osdu.unitservice.middleware.AuthenticationRequestFilter;
import org.opengroup.osdu.unitservice.middleware.AuthenticationService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String[] ALLOWED_URLS = {
      "/",
      "/v2/api-docs",
      "/v3/api-docs",
      "/swagger",
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/info",
      "/webjars/**",
      "/_ah/**",
      "/error",
      "/favicon.ico",
      "/csrf",
      "/api/unit",
      "/api/unit/v2/api-docs",
      "/api/unit/v3/api-docs",
      "/api/unit/swagger-resources/**",
      "/api/unit/swagger-ui.html",
      "/api/unit/webjars/**",
      "/api/unit/_ah/**",
      "/api/unit/error",
      "/api/unit/favicon.ico",
      "/api/unit/csrf" // Required to prevent errors in logs while Swagger is trying to discover a valid csrf token. Should be deleted after the issue on the Swagger's side https://github.com/springfox/springfox/issues/2578 is resolved
  };

  private final AuthenticationService authenticationService;

  public SecurityConfiguration(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    AuthenticationRequestFilter authhenticationFilter = new AuthenticationRequestFilter(authenticationService);
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
