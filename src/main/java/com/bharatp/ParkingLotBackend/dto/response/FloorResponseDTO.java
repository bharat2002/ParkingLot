package com.bharatp.ParkingLotBackend.dto.response;

import java.util.List;

public class FloorResponseDTO {
    private Long id;
    private Long floorNumber;
    private Long parkingLotId;
    private List<Long> spotIds;

    public FloorResponseDTO() {}

    public FloorResponseDTO(Long id, Long floorNumber, Long parkingLotId, List<Long> spotIds) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.parkingLotId = parkingLotId;
        this.spotIds = spotIds;
    }

    // getters
    public Long getId() { return id; }
    public Long getFloorNumber() { return floorNumber; }
    public Long getParkingLotId() { return parkingLotId; }
    public List<Long> getSpotIds() { return spotIds; }
}
