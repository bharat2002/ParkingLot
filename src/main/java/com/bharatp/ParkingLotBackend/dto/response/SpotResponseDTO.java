package com.bharatp.ParkingLotBackend.dto.response;

import com.bharatp.ParkingLotBackend.enums.SpotType;

public class SpotResponseDTO {
    private Long id;
    private String spotNumber;
    private SpotType spotType;
    private boolean isAvailable;
    private Long floorId;

    public SpotResponseDTO() {}

    public SpotResponseDTO(Long id, String spotNumber, SpotType spotType, boolean isAvailable, Long floorId) {
        this.id = id;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isAvailable = isAvailable;
        this.floorId = floorId;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getSpotNumber() { return spotNumber; }
    public SpotType getSpotType() { return spotType; }
    public boolean isAvailable() { return isAvailable; }
    public Long getFloorId() { return floorId; }
}