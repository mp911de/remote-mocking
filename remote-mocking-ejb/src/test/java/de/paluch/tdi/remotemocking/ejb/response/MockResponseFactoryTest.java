package de.paluch.tdi.remotemocking.ejb.response;

import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class MockResponseFactoryTest {

    @Test
    public void testGetResponseIds() {
        String input = "a/b/c";
        List<String> result = new MockResponseFactory().getResponseIds(input);
        assertEquals(3, result.size());
        assertEquals("a/b/c", result.get(0));
        assertEquals("a/b", result.get(1));
        assertEquals("a", result.get(2));

    }
}
