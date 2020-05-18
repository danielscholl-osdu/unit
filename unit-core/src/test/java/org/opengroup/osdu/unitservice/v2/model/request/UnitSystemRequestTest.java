package org.opengroup.osdu.unitservice.v2.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.opengroup.osdu.unitservice.v2.model.UnitEssenceImpl;
import org.opengroup.osdu.unitservice.v2.model.extended.UnitSystemEssenceImpl;
import org.opengroup.osdu.unitservice.v2.request.UnitSystemRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitSystemRequestTest {
    @Test
    public void testEmptyConstructor(){
        UnitSystemRequest request = new UnitSystemRequest();
        assertNotNull(request);
        assertNull(request.getUnitSystemEssence());
    }

    @Test
    public void testEssenceConstructor(){
        UnitSystemEssenceImpl essence = new UnitSystemEssenceImpl("Base.Child1.Child2");
        UnitSystemRequest request = new UnitSystemRequest(essence);
        assertNotNull(request);
        assertNotNull(request.getUnitSystemEssence());
        assertEquals(essence, request.getUnitSystemEssence());
    }
}
