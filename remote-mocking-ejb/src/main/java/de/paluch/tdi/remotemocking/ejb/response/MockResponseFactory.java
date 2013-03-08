package de.paluch.tdi.remotemocking.ejb.response;

import de.paluch.tdi.remotemocking.common.XmlReader;
import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.datastore.DataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.ReadableDataStore;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Retrieve response by hierarchical Id. The Id is hierarchically structured: root/child/subchild. By this logic, there
 * is a default fallback implemented to have a generalisation for responses. When root/child/subchild is not available,
 * so fallback to root/child. When root/child is not available, then fallback to root and then IllegalStateException.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class MockResponseFactory {

    public static final String SEPARATOR = "/";
    private static final Logger LOG = Logger.getLogger(MockResponseFactory.class);

    /**
     * Prepare Response
     *
     * @param responseId
     * @param classes
     * @return MockResponseConnection
     */
    public static <T> T getResponse(String responseId, Class<?>... classes) {
        try {
            String id = new MockResponseFactory().getResponseId(responseId.toLowerCase());
            AbstractDataStoreObject object = get(id);
            return (T) new XmlReader().readXml(object.getInputStream(), classes);
        } catch (IllegalStateException e) {
            LOG.error("Invalid responseId: " + responseId);
            throw new RuntimeException(e);
        } catch (Exception e) {
            LOG.error("Invalid responseId: " + responseId, e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Prepare Response
     *
     * @param responseId
     * @param contextPath
     * @return MockResponseConnection
     */
    public static <T> T getResponse(String responseId, String contextPath) {
        try {
            String id = new MockResponseFactory().getResponseId(responseId.toLowerCase());
            AbstractDataStoreObject object = get(id);
            return (T) new XmlReader().readXml(object.getInputStream(), contextPath);
        } catch (IllegalStateException e) {
            LOG.error("Invalid responseId: " + responseId);
            throw new RuntimeException(e);
        } catch (Exception e) {
            LOG.error("Invalid responseId: " + responseId, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Get ResponseId of available Response.
     *
     * @param responseId
     * @return String
     */
    public String getResponseId(String responseId) {
        List<String> ids = getResponseIds(responseId.toLowerCase());

        for (String id : ids) {
            if (exists(id)) {
                return id;
            }
        }

        throw new IllegalStateException("No responseId available: " + responseId);

    }

    protected List<String> getResponseIds(String responseId) {
        String split[] = responseId.split(SEPARATOR);
        List<String> ids = new ArrayList<String>();

        for (int i = 0; i < split.length; i++) {
            String id = createId(split, i);
            ids.add(id);
        }
        Collections.reverse(ids);

        return ids;
    }

    /**
     * @param split
     * @param segment
     * @return
     */
    private String createId(String[] split, int segment) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < split.length && i < segment + 1; i++) {

            if (i != 0) {
                result.append(SEPARATOR);
            }
            result.append(split[i]);
        }

        return result.toString();

    }

    /**
     * Check, whether Response is available.
     *
     * @param id
     * @return true/false.
     */
    private boolean exists(String id) {

        for (ReadableDataStore store : DataStore.getInstance().getReadableStores()) {
            LOG.debug("store.exists(" + id + ") in " + store);
            boolean result = store.exists(id);
            LOG.debug("store.exists(" + id + ") in " + store + ", result " + result);
            if (result) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get Object from Store.
     *
     * @param id
     * @return AbstractDataStoreObject
     */
    private static AbstractDataStoreObject get(String id) {
        for (ReadableDataStore store : DataStore.getInstance().getReadableStores()) {
            if (store.exists(id)) {
                return store.get(id);
            }
        }
        return null;
    }

}
