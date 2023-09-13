package com.wego.parkingspace.exceptions;

public class TokenGeneratorException extends Exception {
    public TokenGeneratorException(String msg) {
        super(msg);
    }

    public TokenGeneratorException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
