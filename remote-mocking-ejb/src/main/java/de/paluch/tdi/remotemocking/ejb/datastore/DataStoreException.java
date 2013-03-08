package de.paluch.tdi.remotemocking.ejb.datastore;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class DataStoreException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -294429239248813903L;

    /**
     *
     */
    public DataStoreException() {
        super();

    }

    /**
     * @param message
     * @param cause
     */
    public DataStoreException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * @param message
     */
    public DataStoreException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public DataStoreException(Throwable cause) {
        super(cause);

    }

}
