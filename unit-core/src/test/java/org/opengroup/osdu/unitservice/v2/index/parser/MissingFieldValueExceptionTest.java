package org.opengroup.osdu.unitservice.v2.index.parser;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by ZMai on 7/14/2016.
 */
public class MissingFieldValueExceptionTest {

    @Test
    public void constructor() {
        int position = 10;
        MissingFieldValueException exception = new MissingFieldValueException(position);
        assertTrue(exception.getMessage().contains("" + position));
    }

}
