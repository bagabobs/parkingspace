package com.wego.parkingspace.application.service;

import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.port.out.SaveCarParkPort;
import com.wego.parkingspace.application.service.model.CarparkData;
import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.Item;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkApiException;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;
import com.wego.parkingspace.exceptions.PopulateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PopulateCarParkService implements PopulateCarParkUseCase {
    @Value("classpath:HDBCarparkInformation.csv")
    private Resource resourceCsvFile;
    private final SaveCarParkPort saveCarParkPort;
    private final CarParkApiPort carParkApiPort;
    private final LatLongConversionService latLongConversionService;
    private Map<String, CarPark> carParkMap;

    public PopulateCarParkService(SaveCarParkPort saveCarParkPort, CarParkApiPort carParkApiPort,
                                  LatLongConversionService latLongConversionService) {
        this.saveCarParkPort = saveCarParkPort;
        this.carParkApiPort = carParkApiPort;
        this.latLongConversionService = latLongConversionService;
    }

    @Override
    public int populate() throws PopulateException {
        try {
            System.out.println("Populate started");
            long startTime = System.currentTimeMillis();
            List<CarPark> carParks = fetchCarParkFromCsv();
            List<CarPark> resultConvertList = latLongConversionService.converToLatLong(carParks);
            carParkMap = new HashMap<>();
            resultConvertList.forEach(carPark -> {
                carParkMap.put(carPark.getCarParkNumber(), carPark);
            });
            fetchCurrentAvailableSpace();
            int result = saveCarParkPort.saveInBatch(carParkMap.values().stream().toList());
            System.out.println("Time to finish: " + (System.currentTimeMillis() - startTime));
            return result;
        } catch(PersistenceAdapterException tokenGeneratorException) {
            throw new PopulateException(tokenGeneratorException.getMessage(), tokenGeneratorException);
        }
    }

    @Override
    public int updateAvaibility() throws PopulateException {
        try {
            CarparkDataRoot carparkDataRoot = carParkApiPort.fetchCarParkAvailability(ZonedDateTime.now());
            List<CarPark> carParks = new ArrayList<>();
            carparkDataRoot.getItems().forEach(item -> {
                item.getCarparkData().forEach(carparkData -> {
                    CarPark carPark = new CarPark();
                    carParks.add(carPark);
                    carPark.setCarParkNumber(carparkData.getCarparkNumber());
                    carparkData.getCarparkInfo().forEach(carparkInfo -> {
                        carPark.setAvailableLots(carparkInfo.getLotsAvailable());
                        carPark.setTotalLots(carparkInfo.getTotalLots());
                    });
                });
            });
            return saveCarParkPort.updateInBatch(carParks);
        } catch (CarParkApiException e) {
            throw new PopulateException("Cannot fetch car park parking space availability", e);
        } catch (PersistenceAdapterException e) {
            throw new PopulateException("Error getting data from database", e);
        }
    }

    private List<CarPark> fetchCarParkFromCsv() throws PopulateException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("HDBCarparkInformation.csv")))){

            List<CarPark> carParks = new ArrayList<>();
            String lineString;
            int cnt = 0;
            while((lineString = bufferedReader.readLine()) != null) {
                if (cnt > 0) {
                    String[] splitString = lineString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    String carNumber = splitString[0];
                    String address = splitString[1].replaceAll("\"", "");
                    String xCoordinate = splitString[2];
                    String yCoordinate = splitString[3];

                    CarPark carPark = new CarPark(carNumber, address, Double.valueOf(yCoordinate),
                            Double.valueOf(xCoordinate), 0, 0);
                    carParks.add(carPark);
                }
                cnt++;
            }
            return carParks;
        } catch(IOException e) {
            throw new PopulateException("Error when read the HDBCarParkInformation", e);
        }
    }

    private void fetchCurrentAvailableSpace() {
        try {
            CarparkDataRoot carparkDataRoot = carParkApiPort.fetchCarParkAvailability(ZonedDateTime.now());
            List<Item> items = carparkDataRoot.getItems();
            items.forEach(item -> {
                List<CarparkData> carparkDataList = item.getCarparkData();
                carparkDataList.forEach(carparkData -> {
                    CarPark carPark = carParkMap.get(carparkData.getCarparkNumber());
                    if (carPark != null) {
                        carparkData.getCarparkInfo().forEach(carparkInfo -> {
                            carPark.setAvailableLots(carparkInfo.getLotsAvailable());
                            carPark.setTotalLots(carparkInfo.getTotalLots());
                        });
                    }
                });
            });
        } catch(CarParkApiException ignored) {

        }
    }
}
