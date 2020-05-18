package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by ZMai on 6/17/2016.
 */
public class WildcardTest {

    @Test
    public void isWildcardTest() {
        assertTrue(Wildcard.isWildcard('*'));
        assertFalse(Wildcard.isWildcard('?')); // We don't support ? as wildcard
        assertFalse(Wildcard.isWildcard('a'));
    }

    @Test
    public void toStringTest() {
        assertEquals("*", new Wildcard().toString());
    }
}
