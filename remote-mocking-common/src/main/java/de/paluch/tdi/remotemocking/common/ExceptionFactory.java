package de.paluch.tdi.remotemocking.common;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Factory to produce Exceptions based on an Exception-Response.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class ExceptionFactory {

    /**
     * @param exceptionResponse
     */
    public static void throwException(ExceptionResponse exceptionResponse) {

        Throwable exception = createException(exceptionResponse);

        if (exception instanceof RuntimeException) {
            throw (RuntimeException) exception;
        }

        // Generic approach to throw an exception without the need to declare it. Call it hack.
        getUnsafe().throwException(exception);
    }

    /**
     * Create Exception instance by its constructor.
     *
     * @param exceptionResponse
     * @return Throwable
     */
    private static Throwable createException(ExceptionResponse exceptionResponse) {
        try {
            Class<?> exceptionType = Class.forName(exceptionResponse.getExceptionClass());
            for (Constructor<?> constructor : exceptionType.getConstructors()) {

                if (exceptionResponse.getMessage() != null && !exceptionResponse.getMessage().trim().equals("")) {
                    if (constructor.getParameterTypes().length == 1
                            && constructor.getParameterTypes()[0].equals(String.class)) {
                        return (Throwable) constructor.newInstance(exceptionResponse.getMessage());
                    }
                } else {
                    if (constructor.getParameterTypes().length == 0) {
                        return (Throwable) constructor.newInstance();
                    }
                }

            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        throw new IllegalArgumentException("No appropriate constructor for Exception-Class "
                                                   + exceptionResponse.getExceptionClass() + ", message "
                                                   + exceptionResponse.getMessage());
    }

    /**
     * Return an instance of {@link sun.misc.Unsafe}.
     *
     * @return THE instance
     */
    @SuppressWarnings("restriction")
    private static Unsafe getUnsafe() {
        try {
            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);
        } catch (SecurityException e) {
            throw new IllegalStateException("error while obtaining sun.misc.Unsafe", e);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("error while obtaining sun.misc.Unsafe", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("error while obtaining sun.misc.Unsafe", e);
        }
    }


}
