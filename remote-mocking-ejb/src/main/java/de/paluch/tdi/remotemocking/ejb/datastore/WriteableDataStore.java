package de.paluch.tdi.remotemocking.ejb.datastore;

/**
 * Readable Data Store.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface WriteableDataStore {

    /**
     * Puts an object into the store.
     * @param id
     * @param value
     */
    void put(String id, AbstractDataStoreObject value);

    /**
     * Remove an object from the store.
     * @param id
     */
    void remove(String id);

    /**
     * Remove all objects with the given prefix.
     * @param id
     */
    void removeFrom(String id);

    /**
     * Clear the data store.
     */
    void removeAll();
}
