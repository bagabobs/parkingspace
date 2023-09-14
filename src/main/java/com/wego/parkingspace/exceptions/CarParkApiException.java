package com.wego.parkingspace.exceptions;

public class CarParkApiException extends Exception {
    public CarParkApiException(String msg) {
        super(msg);
    }

    public CarParkApiException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
