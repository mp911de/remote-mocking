package de.paluch.tdi.remotemocking.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model for a very simple response.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleResponse {

    @XmlElement(name = "value")
    private String value;

    @XmlElement(name = "exception")
    private ExceptionResponse exception;

    public SimpleResponse() {
    }

    public SimpleResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ExceptionResponse getException() {
        return exception;
    }

    public void setException(ExceptionResponse exception) {
        this.exception = exception;
    }

}
