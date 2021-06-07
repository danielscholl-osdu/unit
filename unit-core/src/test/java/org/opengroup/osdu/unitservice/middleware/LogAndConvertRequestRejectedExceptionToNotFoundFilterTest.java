package org.opengroup.osdu.unitservice.middleware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.opengroup.osdu.core.common.logging.JaxRsDpsLog;
import org.springframework.security.web.firewall.RequestRejectedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class LogAndConvertRequestRejectedExceptionToNotFoundFilterTest {

    @InjectMocks
    private LogAndConvertRequestRejectedExceptionToNotFoundFilter filter;

    @Mock
    private JaxRsDpsLog jaxRsDpsLog;

    @Test
    public void shouldConvertRequestRejectedExceptionToNotFound() throws IOException, ServletException {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        RequestRejectedException exception = new RequestRejectedException("error");
        Mockito.doThrow(exception).when(filterChain).doFilter(httpServletRequest, httpServletResponse);

        filter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        Mockito.verify(jaxRsDpsLog).error("request_rejected: remote=null, user_agent=null, request_url=null", exception);
        Mockito.verify(httpServletResponse).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
