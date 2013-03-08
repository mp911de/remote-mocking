package de.paluch.tdi.remotemocking.web.management.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "dynamicMockDataResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicMockDataResult {

    @XmlElement(name = "mockData")
    private DynamicMockData mockData;

    public DynamicMockDataResult() {
    }

    public DynamicMockDataResult(DynamicMockData mockData) {
        this.mockData = mockData;
    }
}
