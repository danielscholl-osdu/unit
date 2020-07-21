package org.opengroup.osdu.unitservice.index;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by ZMai on 6/16/2016.
 */
public class LuceneConstantsTest {
    public static final String[] SupportedFields = {"id", "content", "type", "reference", "name", "namespace", "source", "symbol",
            "ancestry", "code", "dimensionCode", "quantityCode", "dimensionAnalysis", "state"};
    @Test
    public void supportedFieldTest() {
        for (String fieldName: SupportedFields) {
            assertNotNull(LuceneConstants.resolveFieldName(fieldName));
        }
    }

    @Test
    public void unsupportedFieldTest() {
        String defaultFieldName = LuceneConstants.resolveFieldName("unsupported");
        assertEquals(LuceneConstants.DefaultField, defaultFieldName);

        defaultFieldName = LuceneConstants.resolveFieldName("");
        assertEquals(LuceneConstants.DefaultField, defaultFieldName);

        defaultFieldName = LuceneConstants.resolveFieldName("  ");
        assertEquals(LuceneConstants.DefaultField, defaultFieldName);

        defaultFieldName = LuceneConstants.resolveFieldName(null);
        assertEquals(LuceneConstants.DefaultField, defaultFieldName);
    }
}
