package de.paluch.tdi.remotemocking.ejb.verification;

import com.thoughtworks.xstream.XStream;
import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class MockInvocation extends AbstractDataStoreObject implements Serializable {

    public List<String> arguments;
    public Date lastInvocation;

    /**
     * @param arguments
     */
    public MockInvocation(List<String> arguments) {
        super();
        this.arguments = arguments;
        lastInvocation = new Date();
    }

    /**
     * @param arguments
     */
    public MockInvocation(String... arguments) {
        super();
        this.arguments = Arrays.asList(arguments);
        lastInvocation = new Date();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [arguments=" + arguments + ", lastInvocation=" + lastInvocation + "]";
    }

    @Override
    public InputStream getInputStream() {
        String xml = new XStream().toXML(this);
        return new ByteArrayInputStream(xml.getBytes());
    }
}
