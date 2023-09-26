package com.wego.parkingspace.adapter.out.web;

import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.service.model.*;
import com.wego.parkingspace.config.TokenString;
import com.wego.parkingspace.exceptions.CarParkApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(
//        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:15.3-alpine:///db"
//        })
public class CarParkApiAdapterTest {
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private TokenString tokenString;
//    private CarParkApiPort carParkApiPort;
//
//    @BeforeEach
//    void initialSetup() {
//        carParkApiPort = new CarParkApiAdapter(restTemplate, tokenString);
//    }
//
//
//    @Test
//    void testGetCarParkAvailability() throws CarParkApiException {
//        CarparkDataRoot carparkDataRoot = carParkApiPort.fetchCarParkAvailability(ZonedDateTime.now());
//        List<Item> items = carparkDataRoot.getItems();
//        assertThat(items.per_page()).isEqualTo(1);
//        Item item = items.get(0);
//        List<CarparkData> carparkDataList = item.getCarparkData();
//        assertThat(carparkDataList.per_page()).isGreaterThan(0);
//    }
//
//    @Test
//    void testSvy21Converter() throws CarParkApiException {
//        Svy21Coordinate svy21Coordinate = carParkApiPort.fetchSvy21CoordinateFromConverter(1.319728905,
//                103.8421581);
//        assertThat(svy21Coordinate.yCoordinate()).isEqualTo(146924.54200324757);
//        assertThat(svy21Coordinate.xCoordinate()).isEqualTo(11559656.16256661);
//    }
//
//    @Test
//    void testLatLongConverter() throws CarParkApiException {
//        LatLongCoordinate latLongCoordinate = carParkApiPort.fetchLatLongCoordinateFromConverter(28983.788791079794, 33554.5098132845);
//        assertThat(latLongCoordinate.latitude()).isEqualTo(1.3197295716669164);
//        assertThat(latLongCoordinate.longitude()).isEqualTo(103.84215843333567);
//    }
}
