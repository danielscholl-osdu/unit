package org.opengroup.osdu.unitservice.middleware;

import jakarta.servlet.ServletException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.opengroup.osdu.core.common.http.ResponseHeadersFactory;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UnitFilterTest {

    @Mock
    private DpsHeaders dpsHeaders;

    @Mock
    private ResponseHeadersFactory responseHeadersFactory;

    @Mock
    private FilterChain filterChain;

    @Mock
    private ServletRequest servletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @InjectMocks
    private UnitFilter unitFilter;

    @Before
    public void setUp() {
        unitFilter.ACCESS_CONTROL_ALLOW_ORIGIN_DOMAINS = "*";
    }

    @Test
    public void should_AddHeadersIn_doFilter() throws IOException, ServletException {
        // Arrange
        when(responseHeadersFactory.getResponseHeaders("*")).thenReturn(Map.of("Custom-Header", "Value"));
        when(dpsHeaders.getCorrelationId()).thenReturn("correlation-id");

        // Act
        unitFilter.doFilter(servletRequest, httpServletResponse, filterChain);

        // Assert
        verify(filterChain).doFilter(servletRequest, httpServletResponse);
        verify(dpsHeaders).addCorrelationIdIfMissing();
        verify(httpServletResponse).setHeader("Custom-Header", "Value");
        verify(httpServletResponse).addHeader(DpsHeaders.CORRELATION_ID, "correlation-id");
    }

    @After
    public void tearDown() {
        unitFilter.destroy();
    }

}
