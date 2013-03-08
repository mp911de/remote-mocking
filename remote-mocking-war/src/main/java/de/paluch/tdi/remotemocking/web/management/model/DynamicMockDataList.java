package de.paluch.tdi.remotemocking.web.management.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "dynamicMockDataList")
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicMockDataList {

    @XmlElement(name = "mockData")
    private List<DynamicMockData> list;

    public DynamicMockDataList() {
    }

    public DynamicMockDataList(List<DynamicMockData> list) {
        this.list = list;
    }
}
