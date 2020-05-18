package org.opengroup.osdu.unitservice.v2.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


public class MapStateImplTest {

    @Test
    public void emptyConstructor() {
        MapStateImpl mapState = new MapStateImpl();
        assertNull(mapState.getState());
        assertNull(mapState.getSource());
        assertNull(mapState.getDescription());
    }

    @Test
    public void constructor() {
        String state = "identical";
        String source = "SLB";
        String description = "haha";

        MapStateImpl mapState = new MapStateImpl(state, description, source);
        assertEquals(state, mapState.getState());
        assertEquals(source, mapState.getSource());
        assertEquals(description, mapState.getDescription());
    }
}
