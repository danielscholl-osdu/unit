package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Created by ZMai on 7/14/2016.
 */
public class ItemTest {
    private String field = "Well";
    private String value = "A10";

    @Test
    public void constructor1() {
        ItemValue itemValue = new ItemValue(value);
        Item item = new Item(itemValue);
        assertNull(item.getField());
        assertEquals(itemValue, item.getValue());
        assertFalse(item.isExcluded());
        assertEquals(value, item.toString());
    }

    @Test
    public void constructor2() {
        ItemValue itemValue = new ItemValue(value);
        Item item = new Item(itemValue, true);
        assertNull(item.getField());
        assertEquals(itemValue, item.getValue());
        assertTrue(item.isExcluded());
        assertEquals("-"+ value, item.toString());
    }

    @Test
    public void constructor3() {
        ItemValue itemField = new ItemValue(field);
        ItemValue itemValue = new ItemValue(value);
        Item item = new Item(itemField, itemValue);
        assertEquals(itemField, item.getField());
        assertEquals(itemValue, item.getValue());
        assertFalse(item.isExcluded());
        assertEquals(field + ":"+ value, item.toString());
    }

    @Test
    public void constructor4() {
        ItemValue itemField = new ItemValue(field);
        ItemValue itemValue = new ItemValue(value);
        Item item = new Item(itemField, itemValue, true);
        assertEquals(itemField, item.getField());
        assertEquals(itemValue, item.getValue());
        assertTrue(item.isExcluded());
        assertEquals("-" + field + ":"+ value, item.toString());
    }


}
