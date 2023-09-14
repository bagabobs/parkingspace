package com.wego.parkingspace.application.port.out;

import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.LatLongCoordinate;
import com.wego.parkingspace.application.service.model.Svy21Coordinate;
import com.wego.parkingspace.exceptions.CarParkApiException;

import java.time.ZonedDateTime;

public interface CarParkApiPort {
    CarparkDataRoot fetchCarParkAvailability(ZonedDateTime dateTime) throws CarParkApiException;
    Svy21Coordinate fetchSvy21CoordinateFromConverter(double latitude, double longitude) throws CarParkApiException;
    LatLongCoordinate fetchLatLongCoordinateFromConverter(double xCoordinate, double yCoordinate) throws CarParkApiException;
}
