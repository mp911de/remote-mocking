package de.paluch.tdi.remotemocking.common;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class SimpleResponseTest {

    @Test
    public void testSimpleResponse() {
        SimpleResponse result =  new XmlReader().readXml(
                getClass().getResourceAsStream("/simpleresponse.xml"), SimpleResponse.class);
        assertEquals("responsevalue", result.getValue());
    }

    public void testSimpleException() throws Throwable {
        SimpleResponse result =  new XmlReader().readXml(
                getClass().getResourceAsStream("/simpleresponse-with-exception.xml"), SimpleResponse.class);
        assertNotNull(result.getException());
        assertEquals("java.lang.ClassNotFoundException", result.getException().getExceptionClass());
    }

}
