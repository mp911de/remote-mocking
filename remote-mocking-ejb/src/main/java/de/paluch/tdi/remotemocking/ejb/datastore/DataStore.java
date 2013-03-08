package de.paluch.tdi.remotemocking.ejb.datastore;

import de.paluch.tdi.remotemocking.common.XmlReader;
import de.paluch.tdi.remotemocking.ejb.datastore.config.DynamicDataStoreConfiguration;
import de.paluch.tdi.remotemocking.ejb.datastore.config.FileDataStoreConfiguration;
import de.paluch.tdi.remotemocking.ejb.datastore.config.RemoteMockingConfiguration;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.DynamicDataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.FileDataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.file.FileDataStoreBean;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class DataStore {

    public static final String INVOCATIONS_PREFIX = "invocations/";
    public static final String REMOTE_MOCKING_CONFIG_XML = "/remote-mocking-config.xml";

    private static Logger log = Logger.getLogger(DataStore.class);
    private static DataStore instance = null;

    private FileDataStore fileDataStore;
    private DynamicDataStore dynamicDataStore;


    public static DataStore getInstance() {
        if (instance == null) {
            instance = createInstance();
        }

        return instance;
    }

    public List<ReadableDataStore> getReadableStores() {
        return Arrays.asList(getDynamicDataStore(), getFileDataStore());
    }


    public FileDataStore getFileDataStore() {
        return fileDataStore;
    }

    public DynamicDataStore getDynamicDataStore() {
        return dynamicDataStore;
    }

    /**
     * Create and initialize the Data-Store instance.
     *
     * @return DataStore
     */
    private static DataStore createInstance() {


        DataStore theInstance = new DataStore();
        theInstance.initialize();

        return theInstance;
    }

    private void initialize() {

        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(REMOTE_MOCKING_CONFIG_XML);
            if (is == null) {
                throw new FileNotFoundException("Cannot find resource " + REMOTE_MOCKING_CONFIG_XML);
            }
            RemoteMockingConfiguration configuration = new XmlReader().readXml(is, RemoteMockingConfiguration.class);
            configureFileStore(configuration.fileDataStore);
            configureDynamicStore(configuration.dynamicDataStore);


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException("Cannot initialize DataStore", e);
        }


    }


    private void configureFileStore(FileDataStoreConfiguration fileDataStoreConfiguration) {
        checkFileDataStoreConfiguration(fileDataStoreConfiguration);

        FileDataStoreBean result = new FileDataStoreBean();
        result.setBasePath(fileDataStoreConfiguration.path);
        fileDataStore = result;

    }

    private void checkFileDataStoreConfiguration(FileDataStoreConfiguration fileDataStoreConfiguration) {
        if (fileDataStoreConfiguration == null) {
            throw new IllegalArgumentException("fileDataStore is null");
        }

        if (fileDataStoreConfiguration.path == null) {
            throw new IllegalArgumentException("fileDataStore.path is null");
        }
    }


    private void configureDynamicStore(DynamicDataStoreConfiguration dynamicDataStoreConfiguration) throws Exception {

        checkDynamicDataStoreConfiguration(dynamicDataStoreConfiguration);

        Class<?> theClass = Class.forName(dynamicDataStoreConfiguration.className);
        DynamicDataStore result = (DynamicDataStore) theClass.newInstance();


        if (dynamicDataStoreConfiguration.injectField != null && dynamicDataStoreConfiguration.injectLookupName != null) {
            log.info("Trying to inject " + dynamicDataStoreConfiguration.injectLookupName + " into " +
                             dynamicDataStoreConfiguration.injectField + " (Class: " + theClass.getName() + ")");
            injectReference(dynamicDataStoreConfiguration, theClass, result);
        }

        dynamicDataStore = result;
    }

    private void injectReference(DynamicDataStoreConfiguration dynamicDataStoreConfiguration, Class<?> theClass,
                                 DynamicDataStore result)
            throws NoSuchFieldException, NamingException, IllegalAccessException {
        Field field = theClass.getDeclaredField(dynamicDataStoreConfiguration.injectField);
        field.setAccessible(true);

        Object toInject = InitialContext.doLookup(dynamicDataStoreConfiguration.injectLookupName);
        field.set(result, toInject);
    }

    private void checkDynamicDataStoreConfiguration(DynamicDataStoreConfiguration dynamicDataStoreConfiguration) {
        if (dynamicDataStoreConfiguration == null) {
            throw new IllegalArgumentException("dynamicDataStore is null");
        }

        if (dynamicDataStoreConfiguration.className == null) {
            throw new IllegalArgumentException("dynamicDataStore.className is null");
        }

        if (dynamicDataStoreConfiguration.injectField != null || dynamicDataStoreConfiguration.injectLookupName != null) {
            if (dynamicDataStoreConfiguration.injectField == null || dynamicDataStoreConfiguration.injectLookupName == null) {
                throw new IllegalArgumentException("dynamicDataStore.injectField and dynamicDataStore.injectLookupName must be both either null or not null");
            }
        }
    }


    public static void close() {
        if (instance != null) {
            instance.closeImpl();
            instance = null;
        }
    }

    private void closeImpl() {
        close(getDynamicDataStore());
        close(getFileDataStore());


    }

    private void close(Object dataStore) {
        if (dataStore instanceof Closeable) {
            try {
                Closeable closeable = (Closeable) dataStore;
                closeable.close();
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            }
        }
    }
}
