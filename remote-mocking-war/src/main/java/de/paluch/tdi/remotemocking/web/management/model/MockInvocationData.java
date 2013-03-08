package de.paluch.tdi.remotemocking.web.management.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mockInvocationData")
public class MockInvocationData implements Serializable {


    @XmlAttribute(name = "invocationKey")
    public String invocationKey;

    @XmlElementWrapper(name = "arguments")
    @XmlElement(name = "argument")
    public List<String> arguments;

    /**
     *
     */
    public MockInvocationData() {
        super();

    }

    public MockInvocationData(String invocationKey, String... arguments) {
        this.invocationKey = invocationKey;
        this.arguments = Arrays.asList(arguments);
    }

    public MockInvocationData(String invocationKey, List<String> arguments) {
        this.invocationKey = invocationKey;
        this.arguments = arguments;
    }

}
