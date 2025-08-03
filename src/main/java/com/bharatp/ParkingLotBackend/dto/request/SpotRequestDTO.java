package com.bharatp.ParkingLotBackend.dto.request;

import com.bharatp.ParkingLotBackend.enums.SpotType;

public class SpotRequestDTO {
    private Long floorId;
    private String spotNumber;
    private SpotType spotType;

    public SpotRequestDTO() {}

    public SpotRequestDTO(Long floorId, String spotNumber, SpotType spotType) {
        this.floorId = floorId;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }

    // getters & setters
    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }

    public String getSpotNumber() { return spotNumber; }
    public void setSpotNumber(String spotNumber) { this.spotNumber = spotNumber; }

    public SpotType getSpotType() { return spotType; }
    public void setSpotType(SpotType spotType) { this.spotType = spotType; }
}