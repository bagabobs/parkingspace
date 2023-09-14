package com.wego.parkingspace.adapter.in.web;

import com.wego.parkingspace.application.port.in.CarParkDistanceUseCase;
import com.wego.parkingspace.application.port.in.GetCarParkDistanceCommand;
import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkDistanceException;
import com.wego.parkingspace.exceptions.PopulateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarParkController {
    private final CarParkDistanceUseCase carParkDistanceUseCase;
    private final PopulateCarParkUseCase populateCarParkUseCase;

    public CarParkController(CarParkDistanceUseCase carParkDistanceUseCase,
                             PopulateCarParkUseCase populateCarParkUseCase) {
        this.carParkDistanceUseCase = carParkDistanceUseCase;
        this.populateCarParkUseCase = populateCarParkUseCase;
    }

    @GetMapping("/get_car_parks")
    public ResponseEntity<List<CarPark>> getCarParks(GetCarParkDistanceCommand command) {
        try {
            List<CarPark> carParks = carParkDistanceUseCase.getListOfDistance(command);
            return ResponseEntity.ok().body(carParks);
        } catch (CarParkDistanceException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update_availability")
    public ResponseEntity<Message> updateAvailability() {
        try {
            int dataUpdated = populateCarParkUseCase.updateAvaibility();

            return ResponseEntity.ok(new Message("Data updated " + dataUpdated));
        } catch (PopulateException e) {
            throw new RuntimeException(e);
        }
    }
}
