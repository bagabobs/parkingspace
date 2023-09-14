package com.wego.parkingspace.application.service;

import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.service.model.LatLongCoordinate;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkApiException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Service
public class LatLongConversionService {
    private final CarParkApiPort carParkApiPort;

    public LatLongConversionService(CarParkApiPort carParkApiPort) {
        this.carParkApiPort = carParkApiPort;
    }

    public List<CarPark> converToLatLong(List<CarPark> carParks) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new LatLongConverterTask(carParks));
    }

    private class LatLongConverterTask extends RecursiveTask<List<CarPark>> {
        private static final int THRESHOLD = 500; // Adjust as needed
        private final List<CarPark> carParkList;

        LatLongConverterTask(List<CarPark> carParkList) {
            this.carParkList = carParkList;
        }

        @Override
        protected List<CarPark> compute() {
            if (carParkList.size() <= THRESHOLD) {
                return carParkList.parallelStream()
                        .map(carPark -> {
                            try {
                                LatLongCoordinate latLongCoordinate = carParkApiPort
                                        .fetchLatLongCoordinateFromConverter(carPark.getLongitude(),
                                                carPark.getLatitude());
                                carPark.setLatitude(latLongCoordinate.latitude());
                                carPark.setLongitude(latLongCoordinate.longitude());
                                return carPark;
                            } catch (CarParkApiException e) {
                                throw new RuntimeException(e);
                            }
                        }).toList();
            } else {
                int midpoint = carParkList.size() / 2;
                List<CarPark> leftSublist = carParkList.subList(0, midpoint);
                List<CarPark> rightSublist = carParkList.subList(midpoint, carParkList.size());

                LatLongConverterTask leftTask = new LatLongConverterTask(leftSublist);
                LatLongConverterTask rightTask = new LatLongConverterTask(rightSublist);

                invokeAll(leftTask, rightTask);

                List<CarPark> leftResult = leftTask.join();
                List<CarPark> rightResult = rightTask.join();

                List<CarPark> carParks = new ArrayList<>(leftResult);
                carParks.addAll(rightResult);

                return carParks;
            }
        }
    }
}
