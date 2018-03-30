package com.geditor.io.exporter.exception;

public class FileExportException extends Exception{
    public FileExportException() {
        super();
    }

    public FileExportException(String message) {
        super(message);
    }

    public FileExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileExportException(Throwable cause) {
        super(cause);
    }

    protected FileExportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
