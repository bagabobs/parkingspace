package com.wego.parkingspace.adapter.out.web;

import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.service.model.CarparkData;
import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.Item;
import com.wego.parkingspace.application.service.model.Svy21Coordinate;
import com.wego.parkingspace.config.TokenString;
import com.wego.parkingspace.exceptions.CarParkException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
        })
public class CarParkApiAdapterTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenString tokenString;
    private CarParkApiPort carParkApiPort;

    @BeforeEach
    void initialSetup() {
        carParkApiPort = new CarParkApiAdapter(restTemplate, tokenString);
    }


    @Test
    void testGetCarParkAvailability() throws CarParkException {
        CarparkDataRoot carparkDataRoot = carParkApiPort.fetchCarParkAvailability(ZonedDateTime.now());
        List<Item> items = carparkDataRoot.getItems();
        assertThat(items.size()).isEqualTo(1);
        Item item = items.get(0);
        List<CarparkData> carparkDataList = item.getCarparkData();
        assertThat(carparkDataList.size()).isGreaterThan(0);
    }

    @Test
    void testSvy21Converter() throws CarParkException {
        Svy21Coordinate svy21Coordinate = carParkApiPort.fetchSvy21CoordinateFromConverter(1.319728905,
                103.8421581);
        assertThat(svy21Coordinate.yCoordinate()).isEqualTo(146924.54200324757);
        assertThat(svy21Coordinate.xCoordinate()).isEqualTo(11559656.16256661);
    }
}
