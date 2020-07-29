package org.opengroup.osdu.unitservice.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class ScaleOffsetImplTest {

    @Test
    public void constructor() {
        ScaleOffsetImpl scaleOffset = new ScaleOffsetImpl(1.0, 2.0);
        assertEquals(1.0, scaleOffset.getScale());
        assertEquals(2.0, scaleOffset.getOffset());
        assertTrue(scaleOffset.isValid());
    }

    @Test
    public void isValid() {
        ScaleOffsetImpl scaleOffset = new ScaleOffsetImpl(1.0, 2.0);
        assertTrue(scaleOffset.isValid());

        scaleOffset = new ScaleOffsetImpl(Double.NaN, 2.0);
        assertFalse(scaleOffset.isValid());

        scaleOffset = new ScaleOffsetImpl(1.0, Double.NaN);
        assertFalse(scaleOffset.isValid());

        scaleOffset = new ScaleOffsetImpl(0, 1.0);
        assertFalse(scaleOffset.isValid());
    }

    @Test
    public void hashCodeTest() {
        ScaleOffsetImpl scaleOffset1 = new ScaleOffsetImpl(1.0, 2.0);
        ScaleOffsetImpl scaleOffset2 = new ScaleOffsetImpl(1.0, 2.0);
        assertEquals(scaleOffset1.hashCode(), scaleOffset2.hashCode());

        ScaleOffsetImpl scaleOffset3 = new ScaleOffsetImpl(1.0, 3.0);
        assertFalse(scaleOffset1.hashCode()== scaleOffset3.hashCode());
    }

    @Test
    public void equalsTest() {
        ScaleOffsetImpl scaleOffset1 = new ScaleOffsetImpl(1.0, 2.0);
        ScaleOffsetImpl scaleOffset2 = new ScaleOffsetImpl(1.0, 2.0);
        assertTrue(scaleOffset1.equals(scaleOffset2));

        ScaleOffsetImpl scaleOffset3 = new ScaleOffsetImpl(1.0, 3.0);
        assertFalse(scaleOffset1.equals(scaleOffset3));

        assertFalse(scaleOffset1.equals(null));
        assertFalse(scaleOffset1.equals(new Object()));
    }

}
