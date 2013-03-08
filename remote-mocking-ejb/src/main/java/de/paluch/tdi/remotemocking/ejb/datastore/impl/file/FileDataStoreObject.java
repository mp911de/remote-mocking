package de.paluch.tdi.remotemocking.ejb.datastore.impl.file;

import de.paluch.tdi.remotemocking.ejb.datastore.AbstractDataStoreObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * File-based Data store object.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class FileDataStoreObject extends AbstractDataStoreObject {

    private File file;

    public FileDataStoreObject(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(getClass().getSimpleName());
        sb.append(" [file=").append(file);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
