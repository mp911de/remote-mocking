package de.paluch.tdi.remotemocking.ejb.datastore;

import java.util.List;

/**
 * Browseable Data Store.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface BrowseableDataStore {

    /**
     * List object names below the given prefix.
     * @param prefix
     * @return
     */
    List<String> listNames(String prefix);

    /**
     * List all names.
     * @return
     */
    List<String> listNames();
}
