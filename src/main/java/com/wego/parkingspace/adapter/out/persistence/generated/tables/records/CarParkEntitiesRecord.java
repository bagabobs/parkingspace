/*
 * This file is generated by jOOQ.
 */
package com.wego.parkingspace.adapter.out.persistence.generated.tables.records;


import com.wego.parkingspace.adapter.out.persistence.generated.tables.CarParkEntities;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CarParkEntitiesRecord extends UpdatableRecordImpl<CarParkEntitiesRecord> implements Record6<String, String, Double, Double, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.car_park_entities.car_park_num</code>.
     */
    public void setCarParkNum(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.car_park_entities.car_park_num</code>.
     */
    public String getCarParkNum() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.car_park_entities.address</code>.
     */
    public void setAddress(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.car_park_entities.address</code>.
     */
    public String getAddress() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.car_park_entities.latitude</code>.
     */
    public void setLatitude(Double value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.car_park_entities.latitude</code>.
     */
    public Double getLatitude() {
        return (Double) get(2);
    }

    /**
     * Setter for <code>public.car_park_entities.longitude</code>.
     */
    public void setLongitude(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.car_park_entities.longitude</code>.
     */
    public Double getLongitude() {
        return (Double) get(3);
    }

    /**
     * Setter for <code>public.car_park_entities.total_lots</code>.
     */
    public void setTotalLots(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.car_park_entities.total_lots</code>.
     */
    public Integer getTotalLots() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.car_park_entities.available_lots</code>.
     */
    public void setAvailableLots(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.car_park_entities.available_lots</code>.
     */
    public Integer getAvailableLots() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, Double, Double, Integer, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, Double, Double, Integer, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return CarParkEntities.CAR_PARK_ENTITIES.CAR_PARK_NUM;
    }

    @Override
    public Field<String> field2() {
        return CarParkEntities.CAR_PARK_ENTITIES.ADDRESS;
    }

    @Override
    public Field<Double> field3() {
        return CarParkEntities.CAR_PARK_ENTITIES.LATITUDE;
    }

    @Override
    public Field<Double> field4() {
        return CarParkEntities.CAR_PARK_ENTITIES.LONGITUDE;
    }

    @Override
    public Field<Integer> field5() {
        return CarParkEntities.CAR_PARK_ENTITIES.TOTAL_LOTS;
    }

    @Override
    public Field<Integer> field6() {
        return CarParkEntities.CAR_PARK_ENTITIES.AVAILABLE_LOTS;
    }

    @Override
    public String component1() {
        return getCarParkNum();
    }

    @Override
    public String component2() {
        return getAddress();
    }

    @Override
    public Double component3() {
        return getLatitude();
    }

    @Override
    public Double component4() {
        return getLongitude();
    }

    @Override
    public Integer component5() {
        return getTotalLots();
    }

    @Override
    public Integer component6() {
        return getAvailableLots();
    }

    @Override
    public String value1() {
        return getCarParkNum();
    }

    @Override
    public String value2() {
        return getAddress();
    }

    @Override
    public Double value3() {
        return getLatitude();
    }

    @Override
    public Double value4() {
        return getLongitude();
    }

    @Override
    public Integer value5() {
        return getTotalLots();
    }

    @Override
    public Integer value6() {
        return getAvailableLots();
    }

    @Override
    public CarParkEntitiesRecord value1(String value) {
        setCarParkNum(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord value2(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord value3(Double value) {
        setLatitude(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord value4(Double value) {
        setLongitude(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord value5(Integer value) {
        setTotalLots(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord value6(Integer value) {
        setAvailableLots(value);
        return this;
    }

    @Override
    public CarParkEntitiesRecord values(String value1, String value2, Double value3, Double value4, Integer value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CarParkEntitiesRecord
     */
    public CarParkEntitiesRecord() {
        super(CarParkEntities.CAR_PARK_ENTITIES);
    }

    /**
     * Create a detached, initialised CarParkEntitiesRecord
     */
    public CarParkEntitiesRecord(String carParkNum, String address, Double latitude, Double longitude, Integer totalLots, Integer availableLots) {
        super(CarParkEntities.CAR_PARK_ENTITIES);

        setCarParkNum(carParkNum);
        setAddress(address);
        setLatitude(latitude);
        setLongitude(longitude);
        setTotalLots(totalLots);
        setAvailableLots(availableLots);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CarParkEntitiesRecord
     */
    public CarParkEntitiesRecord(com.wego.parkingspace.adapter.out.persistence.generated.tables.pojos.CarParkEntities value) {
        super(CarParkEntities.CAR_PARK_ENTITIES);

        if (value != null) {
            setCarParkNum(value.getCarParkNum());
            setAddress(value.getAddress());
            setLatitude(value.getLatitude());
            setLongitude(value.getLongitude());
            setTotalLots(value.getTotalLots());
            setAvailableLots(value.getAvailableLots());
            resetChangedOnNotNull();
        }
    }
}
