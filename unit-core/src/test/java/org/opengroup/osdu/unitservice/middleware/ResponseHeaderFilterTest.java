package org.opengroup.osdu.unitservice.middleware;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.opengroup.osdu.core.common.http.ResponseHeadersFactory;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class ResponseHeaderFilterTest {

    @Mock
    private DpsHeaders dpsHeaders;

    @Mock
    private ResponseHeadersFactory responseHeadersFactory;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private ResponseHeaderFilter responseHeaderFilter;

    @Test
    public void test_doFilter_Handles_OptionsRequest() throws Exception {
        when(httpServletRequest.getMethod()).thenReturn("OPTIONS");

        responseHeaderFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void test_doFilter_Handles_GETRequest() throws Exception {
        when(httpServletRequest.getMethod()).thenReturn("GET");

        responseHeaderFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(httpServletResponse, never()).setStatus(HttpServletResponse.SC_OK);
    }

    @After
    public void tearDown() {
        responseHeaderFilter.destroy();
    }

}
