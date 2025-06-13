package org.opengroup.osdu.unitservice.model.request;

import org.junit.Assert;
import org.opengroup.osdu.unitservice.model.extended.UnitSystemEssenceImpl;
import org.opengroup.osdu.unitservice.request.UnitSystemRequest;
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
        Assert.assertEquals(essence, request.getUnitSystemEssence());
    }
}
