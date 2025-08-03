package com.bharatp.ParkingLotBackend.dto.response;

import com.bharatp.ParkingLotBackend.enums.VehicleType;

public class VehicleResponseDTO {
    private Long id;
    private String licensePlate;
    private VehicleType vehicleType;
    private String ownerName;

    public VehicleResponseDTO() {}

    public VehicleResponseDTO(Long id, String licensePlate, VehicleType vehicleType, String ownerName) {
        this.id            = id;
        this.licensePlate  = licensePlate;
        this.vehicleType   = vehicleType;
        this.ownerName     = ownerName;
    }

    // getters
    public Long getId() { return id; }
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getVehicleType() { return vehicleType; }
    public String getOwnerName() { return ownerName; }
}