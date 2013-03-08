package de.paluch.tdi.remotemocking.ejb.datastore.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FileDataStoreConfiguration {

    @XmlAttribute(name = "path")
    public String path;
}
