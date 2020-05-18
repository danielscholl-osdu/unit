package org.opengroup.osdu.unitservice.middleware;

import org.opengroup.osdu.unitservice.util.AppException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Method;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(AuthenticationRequestFilter.class)
public class AuthenticationRequestFilterTest {
    private static final String ROLE1 = "role1";
    private static final String ROLE2 = "role2";

    // @Mock
    // private EntitlementsException entitlementsException;
    // @Mock
    // private IEntitlementsService service;
    // @Mock
    // private IEntitlementsFactory entitlementsFactory;
    // @Mock
    // private JaxRsDpsLog logger;
    // @Mock
    // private Groups groups;
    @InjectMocks
    @Spy
    private AuthenticationRequestFilter sut = new AuthenticationRequestFilter("", null);

    @Before
    public void setUp() throws Exception {
        // MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();
        // headers.put(DpsHeaders.AUTHORIZATION, Collections.singletonList("Bearer geer.fereferv.cefe="));
        // headers.put(DpsHeaders.CONTENT_TYPE, Collections.singletonList("application/json"));
        // when(requestContext.getHeaders()).thenReturn(headers);
        // UriInfo url = mock(UriInfo.class);
        // when(url.getPath()).thenReturn("");

        // when(requestContext.getUriInfo()).thenReturn(url);
        // when(requestContext.getMethod()).thenReturn("POST");

        // Method method = this.getClass().getMethod("rolesAllowedTestMethod");
        // Mockito.when(this.resourceInfo.getResourceMethod()).thenReturn(method);
        // PowerMockito.doReturn(entitlementsFactory).when(sut, "getEntitlementsFactory");
        // PowerMockito.doReturn(logger).when(sut, "getJaxLogger", any());
        // when(entitlementsFactory.create(Matchers.any())).thenReturn(service);
    }

    @Test
    public void should_authorize_user_if_correct_token_passed() throws Exception {
        // Mockito.when(service.getGroups()).thenReturn(groups);
        // when(groups.getMemberEmail()).thenReturn("abcd@xyz.com");
        // sut.filter(requestContext);
    }

    // @Test(expected = AppException.class)
    // public void should_throw_exception_user_if_invalid_token_passed() throws Exception {
    //     Mockito.when(service.getGroups()).thenThrow(entitlementsException);
    //     sut.filter(requestContext);
    // }

    @RolesAllowed({ROLE1, ROLE2})
    public void rolesAllowedTestMethod() {
        // do nothing
    }
}
