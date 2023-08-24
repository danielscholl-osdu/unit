package org.opengroup.osdu.unitservice.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.opengroup.osdu.unitservice.middleware.AuthenticationService;
import org.opengroup.osdu.unitservice.api.UnitApiV3;
import org.opengroup.osdu.unitservice.middleware.AuthenticationRequestFilter;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        UnitApiV3.class,
        AuthenticationRequestFilter.class,
        AuthSecurityConfig.class,
        AuthenticationService.class})
@WebAppConfiguration
public class AuthSecurityConfigTest {
    private MockMvc mockMvc = null;

    @MockBean
    private UnitApiV3 unitApiV3;

    @MockBean
    AuthenticationService authenticationService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testConfigureHttpSecurity() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isNotFound())
            .andReturn();
    }
}