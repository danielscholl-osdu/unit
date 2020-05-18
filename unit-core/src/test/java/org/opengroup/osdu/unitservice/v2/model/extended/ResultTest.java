package org.opengroup.osdu.unitservice.v2.model.extended;

import org.opengroup.osdu.unitservice.v2.model.UnitImpl;
import org.opengroup.osdu.unitservice.v2.model.extended.Result;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class ResultTest {
    List<UnitImpl> units;

    @Before
    public void setup() {
        int n = 5;
        units = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            units.add(new UnitImpl());
        }
    }

    @Test
    public void emptyConstructorTest() {
        Result<UnitImpl> result = new Result<UnitImpl>();
        assertNotNull(result.getItems());
        assertEquals(0, result.getItems().size());
        assertEquals(0, result.getOffset());
        assertEquals(0, result.getCount());
        assertEquals(0, result.getTotalCount());
    }

    @Test
    public void constructorWithItemListOnlyTest() {
        Result<UnitImpl> result = new Result<UnitImpl>(null);
        assertNotNull(result.getItems());
        assertEquals(0, result.getItems().size());
        assertEquals(0, result.getOffset());
        assertEquals(0, result.getCount());
        assertEquals(0, result.getTotalCount());

        int count = units.size();
        result = new Result<UnitImpl>(units);
        assertNotNull(result.getItems());
        assertEquals(count, result.getItems().size());
        assertEquals(0, result.getOffset());
        assertEquals(count, result.getCount());
        assertEquals(count, result.getTotalCount());
    }

    @Test
    public void constructorWithRangeTest() {
        Result<UnitImpl> result = new Result<UnitImpl>(null, 0, 0);
        assertNotNull(result.getItems());
        assertEquals(0, result.getItems().size());
        assertEquals(0, result.getOffset());
        assertEquals(0, result.getCount());
        assertEquals(0, result.getTotalCount());

        int offset = 10;
        int total = 100;
        int count = units.size();
        result = new Result<UnitImpl>(units, offset, total);
        assertNotNull(result.getItems());
        assertEquals(count, result.getItems().size());
        assertEquals(offset, result.getOffset());
        assertEquals(count, result.getCount());
        assertEquals(total, result.getTotalCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeOffsetTest() {
        int offset = -1;
        int total = 100;
        new Result<UnitImpl>(units, offset, total);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNegativeTotalCount() {
        int offset = 0;
        int total = -1;
        new Result<UnitImpl>(units, offset, total);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hasInconsistentCount() {
        int offset = 10;
        int total = units.size();
        new Result<UnitImpl>(units, offset, total);
    }
}
