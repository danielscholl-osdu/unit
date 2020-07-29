package org.opengroup.osdu.unitservice.model.request;

import org.opengroup.osdu.unitservice.request.SearchRequest;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class SearchRequestTest {
    @Test
    public void requestTest() {
        SearchRequest request = new SearchRequest();
        assertNotNull(request);
        assertNull(request.getQuery());
        String queryString = "code:1234";
        SearchRequest query = new SearchRequest(queryString);
        assertNotNull(query);
        assertNotNull(query.getQuery());
        assertEquals(queryString, query.getQuery());
    }
}
