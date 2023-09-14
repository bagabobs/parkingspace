package com.wego.parkingspace.application.service;

import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CarParkRunner implements ApplicationRunner {
    private final PopulateCarParkUseCase carParkUseCase;

    public CarParkRunner(PopulateCarParkUseCase carParkUseCase) {
        this.carParkUseCase = carParkUseCase;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        carParkUseCase.populate();
    }
}
