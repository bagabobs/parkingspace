package com.wego.parkingspace.application.port.out;

import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;

import java.util.List;

public interface SaveCarParkPort {
    int saveInBatch(List<CarPark> carParks) throws PersistenceAdapterException;
    int updateInBatch(List<CarPark> carParks) throws PersistenceAdapterException;
}
