package de.paluch.tdi.remotemocking.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * String-List response.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "response")
public class StringListResponse {

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    protected List<String> value;


    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

}
