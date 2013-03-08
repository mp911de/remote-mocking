package de.paluch.tdi.remotemocking.ejb.datastore;

/**
 * Readable Data Store.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface ReadableDataStore {
    /**
     * Retrieve an object.
     *
     * @param id
     * @return the object or null.
     */
    AbstractDataStoreObject get(String id);

    /**
     * @param id
     * @return true in case the id exists.
     */
    boolean exists(String id);

}
