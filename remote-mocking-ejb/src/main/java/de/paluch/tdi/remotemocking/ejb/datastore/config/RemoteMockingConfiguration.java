package de.paluch.tdi.remotemocking.ejb.datastore.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "remoteMockingConfiguration")
@XmlAccessorType(XmlAccessType.FIELD)
public class RemoteMockingConfiguration {

    @XmlElement(name = "fileDataStore")
    public FileDataStoreConfiguration fileDataStore;

    @XmlElement(name = "dynamicDataStore")
    public DynamicDataStoreConfiguration dynamicDataStore;
}
