package de.paluch.tdi.remotemocking.ejb.datastore.impl.ehcache;

import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.DynamicDataStore;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a
 */
public class EhCacheDataStoreBean implements DynamicDataStore, Closeable {

    private CacheManager cacheManager;
    private Cache ehCache;


    public EhCacheDataStoreBean() {
        cacheManager = CacheManager.create(EhCacheDataStoreBean.class.getResource("/ehcache.xml"));
        ehCache = cacheManager.getCache("remote-mocking");
    }

    @Override
    public List<String> listNames(String prefix) {

        List<String> result = new ArrayList<String>();

        List<String> keys = ehCache.getKeys();
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                result.add(key);
            }
        }

        return result;
    }

    @Override
    public void close() {
        ehCache.dispose();
        cacheManager.shutdown();
    }

    @Override
    public List<String> listNames() {
        return new ArrayList<String>(ehCache.getKeys());
    }

    @Override
    public AbstractDataStoreObject get(String id) {
        Element element = ehCache.get(id);
        if (element != null) {
            return (AbstractDataStoreObject) element.getObjectValue();
        }
        return null;
    }

    @Override
    public void put(String id, AbstractDataStoreObject value) {
        ehCache.put(new Element(id, value));
    }

    @Override
    public void remove(String id) {
        ehCache.remove(id);
    }

    @Override
    public void removeFrom(String id) {
        List<String> keys = listNames(id);
        ehCache.removeAll(keys);
    }

    @Override
    public void removeAll() {
        ehCache.removeAll();
    }

    @Override
    public boolean exists(String id) {
        return ehCache.isKeyInCache(id);
    }
}
