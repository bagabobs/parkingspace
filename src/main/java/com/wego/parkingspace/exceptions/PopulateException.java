package com.wego.parkingspace.exceptions;

public class PopulateException extends Exception {
    public PopulateException(String msg) {
        super(msg);
    }

    public PopulateException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
