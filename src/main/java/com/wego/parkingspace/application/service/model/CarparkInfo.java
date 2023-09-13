package com.wego.parkingspace.application.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarparkInfo {
    @JsonProperty("total_lots")
    private int totalLots;
    @JsonProperty("lot_type")
    private String lotType;
    @JsonProperty("lots_available")
    private int lotsAvailable;

    public int getTotalLots() {
        return totalLots;
    }

    public void setTotalLots(int totalLots) {
        this.totalLots = totalLots;
    }

    public String getLotType() {
        return lotType;
    }

    public void setLotType(String lotType) {
        this.lotType = lotType;
    }

    public int getLotsAvailable() {
        return lotsAvailable;
    }

    public void setLotsAvailable(int lotsAvailable) {
        this.lotsAvailable = lotsAvailable;
    }
}
