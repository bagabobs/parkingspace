package com.wego.parkingspace.exceptions;

public class RepositoryImplementationException extends Exception {
    public RepositoryImplementationException(String msg) {
        super(msg);
    }

    public RepositoryImplementationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
