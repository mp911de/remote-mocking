package de.paluch.tdi.remotemocking.ejb.datastore.impl.file;

import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.datastore.impl.FileDataStore;

import java.io.File;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class FileDataStoreBean implements FileDataStore {

    public static final String XML_SUFFIX = ".xml";
    private String basePath = "";

    @Override
    public AbstractDataStoreObject get(String id) {

        if (exists(id)) {
            return new FileDataStoreObject(getFile(id));
        }

        return null;
    }

    @Override
    public boolean exists(String id) {
        File file = getFile(id);

        return file.exists();
    }

    private File getFile(String id) {
        return new File(getRootFile(), id + XML_SUFFIX);
    }

    private File getRootFile() {
        String location = getBasePath();
        if (location == null) {
            throw new IllegalStateException("Base-Path is not set");
        }

        File file = new File(location);
        if (!file.exists()) {
            throw new IllegalStateException("Location  " + file + " does not exist");
        }

        return file;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
