package com.wego.parkingspace.application.port.out;

import com.wego.parkingspace.application.service.model.LatLongCoordinate;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;

import java.util.List;

public interface LoadCarParkPort {
    List<CarPark> loadCarParksByDistance(LatLongCoordinate latLongCoordinate, int page, int size)
            throws PersistenceAdapterException;

    int loadCarParksSize() throws PersistenceAdapterException;
}
