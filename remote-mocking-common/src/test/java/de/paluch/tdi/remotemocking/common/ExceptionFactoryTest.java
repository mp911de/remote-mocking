package de.paluch.tdi.remotemocking.common;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class ExceptionFactoryTest {

    @Test(expected = ClassNotFoundException.class)
    public void testSimpleException() throws Throwable {
        SimpleResponse result = (SimpleResponse) new XmlReader().readXml(
                getClass().getResourceAsStream("/simpleresponse-with-exception.xml"), SimpleResponse.class);
        ExceptionFactory.throwException(result.getException());
    }

    @Test(expected = NullPointerException.class)
    public void testSimpleNPEException() throws Throwable {
        SimpleResponse result = (SimpleResponse) new XmlReader().readXml(
                getClass().getResourceAsStream("/simpleresponse-with-rt-exception.xml"), SimpleResponse.class);
        ExceptionFactory.throwException(result.getException());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleITException() throws Throwable {
        SimpleResponse result = (SimpleResponse) new XmlReader().readXml(
                getClass().getResourceAsStream("/simpleresponse-with-ite-exception.xml"), SimpleResponse.class);
        ExceptionFactory.throwException(result.getException());
    }

}
