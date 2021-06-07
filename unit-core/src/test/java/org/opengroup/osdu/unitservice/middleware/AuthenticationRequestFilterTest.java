package org.opengroup.osdu.unitservice.middleware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationRequestFilterTest {

    @InjectMocks
    private AuthenticationRequestFilter authenticationRequestFilter;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    public void shouldContinueFilteringWhenAuthenticated() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        Mockito.when(authenticationService.isAuthorized(httpServletRequest, httpServletResponse)).thenReturn(true);

        authenticationRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        Mockito.verify(authenticationService).isAuthorized(httpServletRequest, httpServletResponse);
        Mockito.verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }

    @Test
    public void shouldStopFilteringWhenNotAuthenticated() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        Mockito.when(authenticationService.isAuthorized(httpServletRequest, httpServletResponse)).thenReturn(false);

        authenticationRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        Mockito.verify(authenticationService).isAuthorized(httpServletRequest, httpServletResponse);
        Mockito.verifyNoMoreInteractions(filterChain);
    }
}
