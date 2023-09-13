package com.wego.parkingspace.exceptions;

public class PersistenceAdapterException extends Exception {
    public PersistenceAdapterException(String msg) {
        super(msg);
    }

    public PersistenceAdapterException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
