package de.paluch.tdi.remotemocking.ejb.datastore;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Static implementation having a string field.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class StaticDataStoreObject extends AbstractDataStoreObject {

    private String value;

    public StaticDataStoreObject() {

    }

    public StaticDataStoreObject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(getClass().getSimpleName());
        sb.append(" [value='").append(value).append('\'');
        sb.append(']');
        return sb.toString();
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(value.getBytes());
    }
}
