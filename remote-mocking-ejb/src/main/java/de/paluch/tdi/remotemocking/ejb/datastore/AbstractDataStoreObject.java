package de.paluch.tdi.remotemocking.ejb.datastore;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Represents an abstract object within the data store.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public abstract class AbstractDataStoreObject implements Serializable {

    /**
     *
     * @return Input-Stream for parsing.
     */
    public abstract InputStream getInputStream();
}
