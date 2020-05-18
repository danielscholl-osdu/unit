package org.opengroup.osdu.unitservice.v2.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class MeasurementMapItemImplTest {

    @Test
    public void emptyConstructor() {
        MeasurementMapItemImpl measurementMapItem = new MeasurementMapItemImpl();
        assertNull(measurementMapItem.getFromMeasurement());
        assertNull(measurementMapItem.getToMeasurement());
        assertNull(measurementMapItem.getState());
        assertNull(measurementMapItem.getNote());
    }

    @Test
    public void constructor() {
        MeasurementImpl fromMeasurement = new MeasurementImpl();
        fromMeasurement.setIsBaseMeasurement(false);
        MeasurementImpl toMeasurement = new MeasurementImpl();
        toMeasurement.setIsBaseMeasurement(false);

        String state = "identical";
        String note = "Indicates that the mapsTo conversion factor is identical to the mapsFrom conversion factor";
        MeasurementMapItemImpl measurementMapItem = new MeasurementMapItemImpl(fromMeasurement, toMeasurement, state, note);
        assertEquals(fromMeasurement, measurementMapItem.getFromMeasurement());
        assertEquals(toMeasurement, measurementMapItem.getToMeasurement());
        assertEquals(state, measurementMapItem.getState());
        assertEquals(note, measurementMapItem.getNote());
    }
}
