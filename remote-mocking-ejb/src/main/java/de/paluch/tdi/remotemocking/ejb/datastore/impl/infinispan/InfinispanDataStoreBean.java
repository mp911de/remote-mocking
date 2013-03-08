package de.paluch.tdi.remotemocking.ejb.datastore.impl.infinispan;

import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.DynamicDataStore;
import org.infinispan.Cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a
 */
public class InfinispanDataStoreBean implements DynamicDataStore {

    private Cache<String, AbstractDataStoreObject> infinispanCache;

    @Override
    public List<String> listNames(String prefix) {

        List<String> result = new ArrayList<String>();

        Set<String> keys = infinispanCache.keySet();
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                result.add(key);
            }
        }

        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> listNames() {
        return new ArrayList<String>(infinispanCache.keySet());
    }

    @Override
    public AbstractDataStoreObject get(String id) {
        return infinispanCache.get(id);
    }

    @Override
    public void put(String id, AbstractDataStoreObject value) {
        infinispanCache.put(id, value);
    }

    @Override
    public void remove(String id) {
        infinispanCache.remove(id);
    }

    @Override
    public void removeFrom(String id) {
        List<String> keys = listNames(id);
        for (String key : keys) {
            infinispanCache.remove(key);
        }
    }

    @Override
    public void removeAll() {
        infinispanCache.clear();
    }

    @Override
    public boolean exists(String id) {
        return infinispanCache.containsKey(id);
    }
}
