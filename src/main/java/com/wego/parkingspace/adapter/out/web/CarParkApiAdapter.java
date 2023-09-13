package com.wego.parkingspace.adapter.out.web;

import com.wego.parkingspace.application.port.out.CarParkApiPort;
import com.wego.parkingspace.application.service.model.CarparkDataRoot;
import com.wego.parkingspace.application.service.model.Svy21Coordinate;
import com.wego.parkingspace.config.TokenString;
import com.wego.parkingspace.exceptions.CarParkException;
import com.wego.parkingspace.exceptions.TokenGeneratorException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CarParkApiAdapter implements CarParkApiPort {
    private final RestTemplate restTemplate;
    private final TokenString tokenString;

    public CarParkApiAdapter(RestTemplate restTemplate, TokenString tokenString) {
        this.restTemplate = restTemplate;
        this.tokenString = tokenString;
    }

    @Override
    public CarparkDataRoot fetchCarParkAvailability(ZonedDateTime dateTime) throws CarParkException {
        try {
            ResponseEntity<CarparkDataRoot> carparkDataRootResponse = fetchCarParkAvailabilityApi(dateTime);
            HttpStatusCode httpStatusCode = carparkDataRootResponse.getStatusCode();
            if (httpStatusCode.value() == 403 || httpStatusCode.value() == 401) {
                TokenGenerator tokenGenerator = new TokenGenerator(restTemplate);
                String token = tokenGenerator.fetchToken();
                tokenString.setToken(token);
                carparkDataRootResponse = fetchCarParkAvailabilityApi(dateTime);
                if (carparkDataRootResponse.getStatusCode().is2xxSuccessful()) {
                    return carparkDataRootResponse.getBody();
                } else {
                    throw new CarParkException("Cannot fetch car availability API");
                }
            } else if(httpStatusCode.is2xxSuccessful()) {
                return carparkDataRootResponse.getBody();
            } else {
                throw new CarParkException("Http status code not 200 but " + httpStatusCode.value());
            }
        } catch(TokenGeneratorException exception) {
            throw new CarParkException("Cannot generate token", exception);
        }
    }

    private ResponseEntity<CarparkDataRoot> fetchCarParkAvailabilityApi(ZonedDateTime dateTime) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(tokenString.getToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "https://api.data.gov.sg/v1/transport/carpark-availability?date_time="+ dateTime
                .format(DateTimeFormatter.ISO_INSTANT);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, CarparkDataRoot.class);
    }

    @Override
    public Svy21Coordinate fetchSvy21CoordinateFromConverter(double latitude, double longitude) throws CarParkException {
        try {
            ResponseEntity<Svy21Coordinate> svy21CoordinateResponse = fetchSvy21CoordinateApi(latitude, longitude);
            HttpStatusCode httpStatusCode = svy21CoordinateResponse.getStatusCode();
            if (httpStatusCode.value() == 403 || httpStatusCode.value() == 401) {
                TokenGenerator tokenGenerator = new TokenGenerator(restTemplate);
                String token = tokenGenerator.fetchToken();
                tokenString.setToken(token);
                svy21CoordinateResponse = fetchSvy21CoordinateApi(latitude, longitude);
                if (svy21CoordinateResponse.getStatusCode().is2xxSuccessful()) {
                    return svy21CoordinateResponse.getBody();
                } else {
                    throw new CarParkException("Cannot 4326 format to 3414 format converter API");
                }
            } else if(httpStatusCode.is2xxSuccessful()) {
                return svy21CoordinateResponse.getBody();
            } else {
                throw new CarParkException("Http status code not 200 but " + httpStatusCode.value());
            }
        } catch(TokenGeneratorException exception) {
            throw new CarParkException("Cannot generate token", exception);
        }
    }

    private ResponseEntity<Svy21Coordinate> fetchSvy21CoordinateApi(double latitude, double longitude) {
        String url = "https://developers.onemap.sg/commonapi/convert/4326to3857?latitude=" + latitude
                + "&longitude=" + longitude;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(tokenString.getToken());
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, Svy21Coordinate.class);
    }
}
