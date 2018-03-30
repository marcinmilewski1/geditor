package com.geditor.io.importer.exception;

/**
 * Created by marcin on 19.03.16.
 */
public class ImportFileException extends Exception {
    public ImportFileException(Throwable cause) {
        super(cause);
    }

    public ImportFileException(String message) {
        super(message);
    }

    public ImportFileException() {
        super();
    }
}
