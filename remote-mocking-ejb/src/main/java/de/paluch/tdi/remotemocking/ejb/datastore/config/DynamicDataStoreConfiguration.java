package de.paluch.tdi.remotemocking.ejb.datastore.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicDataStoreConfiguration {

    @XmlAttribute(name = "className")
    public String className;

    @XmlElement(name = "injectField")
    public String injectField;

    @XmlElement(name = "injectLookupName")
    public String injectLookupName;

}
