package com.geditor.io.importer.exception;

/**
 * Created by marcin on 13.03.16.
 */
public class InvalidExtensionException extends Exception {

    public InvalidExtensionException(Throwable cause) {
        super(cause);
    }

    public InvalidExtensionException() {
        super();
    }

    public InvalidExtensionException(String message) {
        super(message);
    }
}
