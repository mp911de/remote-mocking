package de.paluch.tdi.remotemocking.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * XML to Object reader using JAXB.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class XmlReader {

    /**
     * @param is
     * @param classes Classes within the context.
     * @return the Object
     */
    public <T> T readXml(InputStream is, Class<?>... classes) {

        try {
            JAXBContext context = getJAXBContext(classes);
            return unmarshal(is, context);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    /**
     * @param is
     * @param contextPath
     * @return the Object.
     */
    public <T> T readXml(InputStream is, String contextPath) {

        try {
            JAXBContext context = getJAXBContext(contextPath);
            return unmarshal(is, context);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    private <T> T unmarshal(InputStream is, JAXBContext context) throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
        T result = (T) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }

    private JAXBContext getJAXBContext(Class<?>... classes) throws JAXBException {
        return JAXBContext.newInstance(classes);
    }

    private JAXBContext getJAXBContext(String contextPath) throws JAXBException {
        return JAXBContext.newInstance(contextPath);
    }
}
