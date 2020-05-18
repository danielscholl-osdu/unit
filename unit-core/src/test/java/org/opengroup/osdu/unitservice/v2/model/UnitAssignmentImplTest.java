package org.opengroup.osdu.unitservice.v2.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


public class UnitAssignmentImplTest {

    @Test
    public void emptyConstructor() {
        UnitAssignmentImpl unitAssignment = new UnitAssignmentImpl();
        assertNull(unitAssignment.getMeasurement());
        assertNull(unitAssignment.getUnit());
        assertNull(unitAssignment.getLastModified());
    }

    @Test
    public void constructor() {
        MeasurementImpl measurement = new MeasurementImpl();
        measurement.setIsBaseMeasurement(false);
        UnitImpl unit = new UnitImpl();
        String lastModified = "20160712";
        UnitAssignmentImpl unitAssignment = new UnitAssignmentImpl(measurement, unit);
        assertEquals(measurement, unitAssignment.getMeasurement());
        assertEquals(unit, unitAssignment.getUnit());

        unitAssignment.setLastModified(lastModified);
        assertEquals(lastModified, unitAssignment.getLastModified());
    }
}
