package com.wego.parkingspace.application.service;

import com.wego.parkingspace.application.port.in.CarParkDistanceUseCase;
import com.wego.parkingspace.application.port.in.GetCarParkDistanceCommand;
import com.wego.parkingspace.application.port.out.LoadCarParkPort;
import com.wego.parkingspace.application.service.model.LatLongCoordinate;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkDistanceException;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarParkDistanceService implements CarParkDistanceUseCase {
    private final LoadCarParkPort loadCarParkPort;

    public CarParkDistanceService(LoadCarParkPort loadCarParkPort) {
        this.loadCarParkPort = loadCarParkPort;
    }

    @Override
    public List<CarPark> getListOfDistance(GetCarParkDistanceCommand command) throws CarParkDistanceException {
        try {
            LatLongCoordinate latLongCoordinate = new LatLongCoordinate(command.latitude(), command.longitude());
            return loadCarParkPort.loadCarParksByDistance(latLongCoordinate, command.page(), command.size());
        } catch (PersistenceAdapterException e) {
            throw new CarParkDistanceException("Cannot get data from database", e);
        }
    }
}
