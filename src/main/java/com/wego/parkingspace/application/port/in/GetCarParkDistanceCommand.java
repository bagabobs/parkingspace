package com.wego.parkingspace.application.port.in;

public record GetCarParkDistanceCommand(double longitude, double latitude, int page, int size) {}
