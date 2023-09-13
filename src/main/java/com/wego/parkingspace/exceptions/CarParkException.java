package com.wego.parkingspace.exceptions;

public class CarParkException extends Exception {
    public CarParkException(String msg) {
        super(msg);
    }

    public CarParkException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
