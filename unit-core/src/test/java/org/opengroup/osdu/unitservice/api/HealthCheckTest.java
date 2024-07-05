package org.opengroup.osdu.unitservice.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

public class HealthCheckTest {

    private HealthCheck sut;

    @Before
    public void setup() {
        this.sut = new HealthCheck();
    }

    @Test
    public void should_returnHttp200_when_checkLiveness() {
        assertEquals(HttpStatus.OK, this.sut.livenessCheck().getStatusCode());
    }

    @Test
    public void should_returnHttp200_when_checkReadiness() {
        assertEquals(HttpStatus.OK, this.sut.readinessCheck().getStatusCode());
    }

}
