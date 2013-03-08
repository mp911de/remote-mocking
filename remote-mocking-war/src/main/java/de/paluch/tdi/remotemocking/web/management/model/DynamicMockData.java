package de.paluch.tdi.remotemocking.web.management.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicMockData implements Serializable {

    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "data")
    private String data;

    public DynamicMockData() {
    }

    /**
     * @param id
     * @param data
     */
    public DynamicMockData(String id, String data) {
        super();
        this.id = id;
        this.data = data;
    }

}
