package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by ZMai on 7/14/2016.
 */
public class UnexpectedParameterExceptionTest {
    @Test
    public void constructor() {
        String message = "field value";
        int position = 10;
        UnexpectedParameterException exception = new UnexpectedParameterException(message, position);
        assertTrue(exception.getMessage().contains(message));
        assertTrue(exception.getMessage().contains("" + position));
    }
}
