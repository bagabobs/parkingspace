package com.wego.parkingspace.domain;

public class CarPark {
    private String carParkNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer totalLots;
    private Integer availableLots;

    public CarPark() {}

    public CarPark(String carParkNumber, String address, Double latitude, Double longitude, Integer totalLots, Integer availableLots) {
        this.carParkNumber = carParkNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalLots = totalLots;
        this.availableLots = availableLots;
    }

    public String getCarParkNumber() {
        return carParkNumber;
    }

    public void setCarParkNumber(String carParkNumber) {
        this.carParkNumber = carParkNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(Integer totalLots) {
        this.totalLots = totalLots;
    }

    public Integer getAvailableLots() {
        return availableLots;
    }

    public void setAvailableLots(Integer availableLots) {
        this.availableLots = availableLots;
    }

    @Override
    public String toString() {
        return "Car Park Number: " + carParkNumber + ", latitude: " + latitude + ", longitude: " + longitude;
    }
}
