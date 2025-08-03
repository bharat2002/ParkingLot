package com.bharatp.ParkingLotBackend.dto.request;

public class FloorRequestDTO {
    private Long parkingLotId;
    private Long floorNumber;

    public FloorRequestDTO() {}

    public FloorRequestDTO(Long parkingLotId, Long floorNumber) {
        this.parkingLotId = parkingLotId;
        this.floorNumber = floorNumber;
    }

    // getters & setters
    public Long getParkingLotId() { return parkingLotId; }
    public void setParkingLotId(Long parkingLotId) { this.parkingLotId = parkingLotId; }

    public Long getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Long floorNumber) { this.floorNumber = floorNumber; }
}
