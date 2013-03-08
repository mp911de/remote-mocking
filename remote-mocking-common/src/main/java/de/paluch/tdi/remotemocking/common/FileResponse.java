package de.paluch.tdi.remotemocking.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Byte-Based response.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "response")
public class FileResponse {

    @XmlElement
    private byte[] value;


    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

}
