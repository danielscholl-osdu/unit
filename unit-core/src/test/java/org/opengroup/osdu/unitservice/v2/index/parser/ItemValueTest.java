package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ZMai on 7/14/2016.
 */
public class ItemValueTest {
    private String value = "A10";

    @Test
    public void constructor1() {
        ItemValue itemValue = new ItemValue(value);
        assertEquals(value, itemValue.getValue());
        assertFalse(itemValue.isQuoted());
        assertEquals(value, itemValue.toString());
    }

    @Test
    public void constructor2() {
        ItemValue itemValue = new ItemValue(value, true);
        assertEquals(value, itemValue.getValue());
        assertTrue(itemValue.isQuoted());
        assertEquals("\"" + value + "\"", itemValue.toString());
    }
}
