package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ZMai on 6/15/2016.
 */
public class KeywordParserTest {

    @Test
    public void parseEmptyKeywords() throws Exception
    {
        KeywordParser parser = new KeywordParser();

        List<List<Item>> items = parser.parse(null);
        assertTrue(items != null);
        assertEquals(0, items.size());

        items = parser.parse("");
        assertTrue(items != null);
        assertEquals(0, items.size());

        items = parser.parse("    ");
        assertTrue(items != null);
        assertEquals(0, items.size());
    }

    @Test
    public void parseNormalKeywords() throws Exception
    {
        KeywordParser parser = new KeywordParser();

        List<List<Item>> itemOrList = parser.parse("Hello");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        
        List<Item> itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());
        Item item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() == null);
        assertTrue(item.getValue() != null);
        assertEquals("Hello", item.getValue().getValue());
        assertFalse(item.getValue().isQuoted());

        itemOrList = parser.parse(" Hello United States  ");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(3, itemAndList.size());

        Item firstItem = itemAndList.get(0);
        assertFalse(firstItem.isExcluded());
        assertTrue(firstItem.getField() == null);
        assertTrue(firstItem.getValue() != null);
        assertEquals("Hello", firstItem.getValue().getValue());
        assertFalse(firstItem.getValue().isQuoted());

        Item secondItem = itemAndList.get(1);
        assertFalse(secondItem.isExcluded());
        assertTrue(secondItem.getField() == null);
        assertTrue(secondItem.getValue() != null);
        assertEquals("United", secondItem.getValue().getValue());
        assertFalse(secondItem.getValue().isQuoted());

        Item thirdItem = itemAndList.get(2);
        assertFalse(thirdItem.isExcluded());
        assertTrue(thirdItem.getField() == null);
        assertTrue(thirdItem.getValue() != null);
        assertEquals("States", thirdItem.getValue().getValue());
        assertFalse(thirdItem.getValue().isQuoted());
    }

    @Test
    public void parseKeywordsWithOR() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Hello World OR China OR United State");
        assertTrue(itemOrList != null);
        assertEquals(3, itemOrList.size());
        assertEquals(2, itemOrList.get(0).size());
        assertEquals(1, itemOrList.get(1).size());
        assertEquals(2, itemOrList.get(2).size());

        itemOrList = parser.parse("OR OR OR");
        assertTrue(itemOrList != null);
        assertEquals(0, itemOrList.size());

        itemOrList = parser.parse("Hello World OR OR OR China OR United State");
        assertTrue(itemOrList != null);
        assertEquals(3, itemOrList.size());
        assertEquals(2, itemOrList.get(0).size());
        assertEquals(1, itemOrList.get(1).size());
        assertEquals(2, itemOrList.get(2).size());
    }

    @Test
    public void parseKeywordsWithFields() throws Exception
    {
        KeywordParser parser = new KeywordParser();

        List<List<Item>> itemOrList = parser.parse("Name:A10");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        List<Item> itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        Item item = itemAndList.get(0);
        assertTrue(item.getField() != null);
        assertEquals("Name", item.getField().getValue());
        assertFalse(item.getField().isQuoted());
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertFalse(item.getValue().isQuoted());

        itemOrList = parser.parse("Name:A10 DataType:Well");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(2, itemAndList.size());

        Item firstItem = itemAndList.get(0);
        assertFalse(firstItem.isExcluded());
        assertTrue(firstItem.getField() != null);
        assertEquals("Name", firstItem.getField().getValue());
        assertFalse(firstItem.getField().isQuoted());
        assertTrue(firstItem.getValue() != null);
        assertEquals("A10", firstItem.getValue().getValue());
        assertFalse(firstItem.getValue().isQuoted());

        Item secondItem = itemAndList.get(1);
        assertFalse(secondItem.isExcluded());
        assertTrue(secondItem.getField() != null);
        assertEquals("DataType", secondItem.getField().getValue());
        assertFalse(secondItem.getField().isQuoted());
        assertTrue(secondItem.getValue() != null);
        assertEquals("Well", secondItem.getValue().getValue());
        assertFalse(secondItem.getValue().isQuoted());

        itemOrList = parser.parse("Name:A10 Well");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(2, itemAndList.size());

        firstItem = itemAndList.get(0);
        assertFalse(firstItem.isExcluded());
        assertTrue(firstItem.getField() != null);
        assertEquals("Name", firstItem.getField().getValue());
        assertFalse(firstItem.getField().isQuoted());
        assertTrue(firstItem.getValue() != null);
        assertEquals("A10", firstItem.getValue().getValue());
        assertFalse(firstItem.getValue().isQuoted());

        secondItem = itemAndList.get(1);
        assertFalse(secondItem.isExcluded());
        assertTrue(secondItem.getField() == null);
        assertTrue(secondItem.getValue() != null);
        assertEquals("Well", secondItem.getValue().getValue());
        assertFalse(secondItem.getValue().isQuoted());
    }

    @Test(expected = MissingFieldValueException.class)
    public void parseKeyword1WithInvalidFieldOperator() throws  Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Name: A10");
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword2WithInvalidFieldOperator() throws  Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Name :A10");
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword3WithInvalidFieldOperator() throws  Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Name : A10");
    }

    @Test
    public void parseKeywordsWithExcluded() throws Exception
    {
        KeywordParser parser = new KeywordParser();

        List<List<Item>> itemOrList = parser.parse("-A10");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        List<Item> itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        Item item = itemAndList.get(0);
        assertTrue(item.isExcluded());
        assertTrue(item.getField() == null);
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertFalse(item.getValue().isQuoted());

        itemOrList = parser.parse("-Name:A10");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        item = itemAndList.get(0);
        assertTrue(item.isExcluded());
        assertTrue(item.getField() != null);
        assertEquals("Name", item.getField().getValue());
        assertFalse(item.getField().isQuoted());
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertFalse(item.getValue().isQuoted());
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword1WithInvalidExcluded() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("-");
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword2WithInvalidExcluded() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("--");
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword3WithInvalidExcluded() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Name:-A10");
    }

    @Test
    public void parseKeywordsWithQuote() throws Exception
    {
        KeywordParser parser = new KeywordParser();

        List<List<Item>> itemOrList = parser.parse("\"A10\"");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        List<Item> itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        Item item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() == null);
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertTrue(item.getValue().isQuoted());

        itemOrList = parser.parse("\"Name:A10\"");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() == null);
        assertTrue(item.getValue() != null);
        assertEquals("Name:A10", item.getValue().getValue());
        assertTrue(item.getValue().isQuoted());

        itemOrList = parser.parse("\" Name:A10 A16 \"");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() == null);
        assertTrue(item.getValue() != null);
        assertEquals(" Name:A10 A16 ", item.getValue().getValue());
        assertTrue(item.getValue().isQuoted());

        itemOrList = parser.parse("\"   \"");
        assertTrue(itemOrList != null);
        assertEquals(0, itemOrList.size());

        itemOrList = parser.parse("Name:\"A10\"");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() != null);
        assertEquals("Name", item.getField().getValue());
        assertFalse(item.getField().isQuoted());
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertTrue(item.getValue().isQuoted());

        itemOrList = parser.parse("Name:\"A10\"");
        assertTrue(itemOrList != null);
        assertEquals(1, itemOrList.size());
        itemAndList = itemOrList.get(0);
        assertEquals(1, itemAndList.size());

        item = itemAndList.get(0);
        assertFalse(item.isExcluded());
        assertTrue(item.getField() != null);
        assertEquals("Name", item.getField().getValue());
        assertFalse(item.getField().isQuoted());
        assertTrue(item.getValue() != null);
        assertEquals("A10", item.getValue().getValue());
        assertTrue(item.getValue().isQuoted());
    }

    @Test
    public void parseKeywordsWithEscapedQuote() throws Exception
    {
        String keyword =
                "\"Ocean-Host_DefaultWellVelocity\":\"<key en=\\\"EpiWell_WellBore\\\" mv=\\\"2.9\\\" dn=\\\"OpenWorks_GDA\\\" tn=\\\"OpenWorks\\\" tv=\\\"R5000\\\" pn=\\\"GDA_MASTER\\\"><se n=\\\"gdiWellEntire_t\\\" id=\\\"1\\\"/><a n=\\\"wellid\\\" ei=\\\"1\\\">548</a></key>\"";
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse(keyword);
        assertEquals(1, itemOrList.size());
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword1WithInvalidQuote() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("Name:\" \"");
    }

    @Test(expected = UnexpectedParameterException.class)
    public void parseKeyword2WithInvalidQuote() throws Exception
    {
        KeywordParser parser = new KeywordParser();
        List<List<Item>> itemOrList = parser.parse("\" \":A10");
    }

}
