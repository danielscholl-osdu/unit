package org.opengroup.osdu.unitservice.v2.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


public class MeasurementDeprecationInfoImplTest {
    MeasurementDeprecationInfoImpl deprecationInfo;

    @Before
    public void setup() {
        deprecationInfo = new MeasurementDeprecationInfoImpl();
    }

    @Test
    public void emptyConstructor() {
        assertNull(deprecationInfo.getState());
        assertNull(deprecationInfo.getRemarks());
        assertNull(deprecationInfo.getSupersededByUnitMeasurement());
    }

    @Test
    public void stateAccessor() {
        String state = "identical";
        deprecationInfo.setState(state);
        assertEquals(state, deprecationInfo.getState());

        String toString = "P-DeprecationInfo " + state;
        assertEquals(toString, deprecationInfo.toString());
    }

    @Test
    public void remarksAccessor() {
        String remarks = "deprecation state this indicates that the superseded item is identical.";
        deprecationInfo.setRemarks(remarks);
        assertEquals(remarks, deprecationInfo.getRemarks());
    }
}
