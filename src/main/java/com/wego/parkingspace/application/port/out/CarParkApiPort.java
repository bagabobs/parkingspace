package com.wego.parkingspace.application.port.out;

import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.Svy21Coordinate;
import com.wego.parkingspace.exceptions.CarParkException;

import java.time.ZonedDateTime;
import java.util.List;

public interface CarParkApiPort {
    CarparkDataRoot fetchCarParkAvailability(ZonedDateTime dateTime) throws CarParkException;
    Svy21Coordinate fetchSvy21CoordinateFromConverter(double latitude, double longitude) throws CarParkException;
}
