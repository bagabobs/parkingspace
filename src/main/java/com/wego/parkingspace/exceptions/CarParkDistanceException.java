package com.wego.parkingspace.exceptions;

public class CarParkDistanceException extends Exception {
    public CarParkDistanceException(String msg) {
        super(msg);
    }

    public CarParkDistanceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
