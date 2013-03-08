package de.paluch.tdi.remotemocking.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Very simple XML writer using JAXB.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class XmlWriter {

    public static void write(Object jaxbObject) {
        try {
            JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(jaxbObject, new File("/tmp/" + jaxbObject.getClass().getSimpleName() + ".xml"));
            marshaller.marshal(jaxbObject, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
