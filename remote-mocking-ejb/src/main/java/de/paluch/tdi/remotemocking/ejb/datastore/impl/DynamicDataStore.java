package de.paluch.tdi.remotemocking.ejb.datastore.impl;

import de.paluch.tdi.remotemocking.ejb.datastore.BrowseableDataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.ReadableDataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.WriteableDataStore;

/**
 * Interface for managing the Cache.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public interface DynamicDataStore extends ReadableDataStore, BrowseableDataStore, WriteableDataStore {

}
