/*
 * This file is generated by jOOQ.
 */
package com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CarParkEntities implements Serializable {

    private static final long serialVersionUID = 1L;

    private String carParkNum;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer totalLots;
    private Integer availableLots;

    public CarParkEntities() {}

    public CarParkEntities(CarParkEntities value) {
        this.carParkNum = value.carParkNum;
        this.address = value.address;
        this.latitude = value.latitude;
        this.longitude = value.longitude;
        this.totalLots = value.totalLots;
        this.availableLots = value.availableLots;
    }

    public CarParkEntities(
        String carParkNum,
        String address,
        Double latitude,
        Double longitude,
        Integer totalLots,
        Integer availableLots
    ) {
        this.carParkNum = carParkNum;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalLots = totalLots;
        this.availableLots = availableLots;
    }

    /**
     * Getter for <code>public.car_park_entities.car_park_num</code>.
     */
    public String getCarParkNum() {
        return this.carParkNum;
    }

    /**
     * Setter for <code>public.car_park_entities.car_park_num</code>.
     */
    public void setCarParkNum(String carParkNum) {
        this.carParkNum = carParkNum;
    }

    /**
     * Getter for <code>public.car_park_entities.address</code>.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for <code>public.car_park_entities.address</code>.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for <code>public.car_park_entities.latitude</code>.
     */
    public Double getLatitude() {
        return this.latitude;
    }

    /**
     * Setter for <code>public.car_park_entities.latitude</code>.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter for <code>public.car_park_entities.longitude</code>.
     */
    public Double getLongitude() {
        return this.longitude;
    }

    /**
     * Setter for <code>public.car_park_entities.longitude</code>.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter for <code>public.car_park_entities.total_lots</code>.
     */
    public Integer getTotalLots() {
        return this.totalLots;
    }

    /**
     * Setter for <code>public.car_park_entities.total_lots</code>.
     */
    public void setTotalLots(Integer totalLots) {
        this.totalLots = totalLots;
    }

    /**
     * Getter for <code>public.car_park_entities.available_lots</code>.
     */
    public Integer getAvailableLots() {
        return this.availableLots;
    }

    /**
     * Setter for <code>public.car_park_entities.available_lots</code>.
     */
    public void setAvailableLots(Integer availableLots) {
        this.availableLots = availableLots;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CarParkEntities other = (CarParkEntities) obj;
        if (this.carParkNum == null) {
            if (other.carParkNum != null)
                return false;
        }
        else if (!this.carParkNum.equals(other.carParkNum))
            return false;
        if (this.address == null) {
            if (other.address != null)
                return false;
        }
        else if (!this.address.equals(other.address))
            return false;
        if (this.latitude == null) {
            if (other.latitude != null)
                return false;
        }
        else if (!this.latitude.equals(other.latitude))
            return false;
        if (this.longitude == null) {
            if (other.longitude != null)
                return false;
        }
        else if (!this.longitude.equals(other.longitude))
            return false;
        if (this.totalLots == null) {
            if (other.totalLots != null)
                return false;
        }
        else if (!this.totalLots.equals(other.totalLots))
            return false;
        if (this.availableLots == null) {
            if (other.availableLots != null)
                return false;
        }
        else if (!this.availableLots.equals(other.availableLots))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.carParkNum == null) ? 0 : this.carParkNum.hashCode());
        result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = prime * result + ((this.latitude == null) ? 0 : this.latitude.hashCode());
        result = prime * result + ((this.longitude == null) ? 0 : this.longitude.hashCode());
        result = prime * result + ((this.totalLots == null) ? 0 : this.totalLots.hashCode());
        result = prime * result + ((this.availableLots == null) ? 0 : this.availableLots.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarParkEntities (");

        sb.append(carParkNum);
        sb.append(", ").append(address);
        sb.append(", ").append(latitude);
        sb.append(", ").append(longitude);
        sb.append(", ").append(totalLots);
        sb.append(", ").append(availableLots);

        sb.append(")");
        return sb.toString();
    }
}
