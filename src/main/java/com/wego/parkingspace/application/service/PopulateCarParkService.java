package com.wego.parkingspace.application.service;

import com.wego.parkingspace.application.port.in.PopulateCarParkUseCase;
import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.port.out.SaveCarParkPort;
import com.wego.parkingspace.application.service.model.CarparkData;
import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.Item;
import com.wego.parkingspace.domain.CarPark;
import com.wego.parkingspace.exceptions.CarParkException;
import com.wego.parkingspace.exceptions.PersistenceAdapterException;
import com.wego.parkingspace.exceptions.PopulateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PopulateCarParkService implements PopulateCarParkUseCase {
    @Value("classpath:HDBCarparkInformation.csv")
    private Resource resourceCsvFile;
    private final SaveCarParkPort saveCarParkPort;
    private final CarParkApiPort carParkApiPort;
    private Map<String, CarPark> carParkMap;

    public PopulateCarParkService(SaveCarParkPort saveCarParkPort, CarParkApiPort carParkApiPort) {
        this.saveCarParkPort = saveCarParkPort;
        this.carParkApiPort = carParkApiPort;
    }

    @Override
    public int populate() throws PopulateException {
        try {
            carParkMap = new HashMap<>();
            fetchCarParkFromCsv();
            fetchCurrentAvailableSpace();
            return saveCarParkPort.saveInBatch(carParkMap.values().stream().toList());
        } catch(PersistenceAdapterException tokenGeneratorException) {
            throw new PopulateException(tokenGeneratorException.getMessage(), tokenGeneratorException);
        }
    }

    private void fetchCarParkFromCsv() throws PopulateException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceCsvFile.getFile()))) {
            String lineString;
            int cnt = 0;
            while((lineString = bufferedReader.readLine()) != null) {
                if (cnt > 0) {
                    String[] splitString = lineString.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    String carNumber = splitString[0];
                    String address = splitString[1].replaceAll("\"", "");
                    String xCoordinate = splitString[2];
                    String yCoordinate = splitString[3];

                    CarPark carPark = new CarPark(carNumber, address, Double.valueOf(xCoordinate),
                            Double.valueOf(yCoordinate), 0, 0);
                    carParkMap.put(carNumber, carPark);
                }
                cnt++;
            }
        } catch(IOException e) {
            throw new PopulateException("Error when read the HDBCarParkInformation");
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
        } catch(CarParkException ignored) {

        }
    }
}
