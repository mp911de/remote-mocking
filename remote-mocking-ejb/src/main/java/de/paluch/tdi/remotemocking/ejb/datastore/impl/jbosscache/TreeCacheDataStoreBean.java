package de.paluch.tdi.remotemocking.ejb.datastore.impl.jbosscache;

import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.datastore.DataStoreException;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.DynamicDataStore;
import org.jboss.cache.CacheException;
import org.jboss.cache.Fqn;
import org.jboss.cache.TreeCacheMBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class TreeCacheDataStoreBean implements DynamicDataStore {

    private TreeCacheMBean treeCache;

    @Override
    public AbstractDataStoreObject get(String id) {
        try {
            String regionKey = id;
            regionKey = regionKey.replaceAll("\\/\\/", "\\/");
            return (AbstractDataStoreObject) treeCache.get(Fqn.fromString(regionKey), "value");
        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    @Override
    public void put(String id, AbstractDataStoreObject value) {
        try {
            treeCache.put(Fqn.fromString(id), "value", value);
        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    @Override
    public void removeFrom(String id) {
        try {
            treeCache.remove(Fqn.fromString(id));
        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String region) {
        try {
            treeCache.remove(Fqn.fromString(region));
        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAll() {
        try {
            treeCache.remove(Fqn.fromString(Fqn.SEPARATOR));
        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> listNames() {
        return listNames("");
    }

    /**
     * @param result
     * @param prefix
     * @throws CacheException
     */
    private void populate(Set<String> result, String prefix) throws CacheException {

        Set<String> children = treeCache.getChildrenNames(prefix + "/");
        if (children != null) {
            for (String child : children) {

                if (prefix.endsWith("/")) {
                    populate(result, prefix + child);
                } else {
                    populate(result, prefix + "/" + child);
                }

                String key = null;
                if (prefix.equals("")) {
                    key = "/" + child;
                } else {
                    key = prefix + child;
                }

                key = key.replaceAll("\\/\\/", "\\/");

                result.add(key);
            }
        }
        result.add(prefix.replaceAll("\\/\\/", "\\/"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> listNames(String prefix) {
        Set<String> result = new HashSet<String>();

        try {
            populate(result, prefix);
            return new ArrayList<String>(result);

        } catch (CacheException e) {
            throw new DataStoreException(e);
        }
    }

    @Override
    public boolean exists(String id) {
        return treeCache.exists(Fqn.fromString(id));
    }
}
