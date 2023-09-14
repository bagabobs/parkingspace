package com.wego.parkingspace.application.port.in;

import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkDistanceException;

import java.util.List;

public interface CarParkDistanceUseCase {
    List<CarPark> getListOfDistance(GetCarParkDistanceCommand command) throws CarParkDistanceException;
}
