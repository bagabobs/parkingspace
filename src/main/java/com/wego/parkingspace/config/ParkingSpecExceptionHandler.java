package com.wego.parkingspace.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ParkingSpecExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> invalidData(RuntimeException ex, WebRequest webRequest) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
