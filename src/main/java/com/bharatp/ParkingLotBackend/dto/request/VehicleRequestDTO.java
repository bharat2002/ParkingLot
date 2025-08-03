package com.bharatp.ParkingLotBackend.dto.request;

import com.bharatp.ParkingLotBackend.enums.VehicleType;

public class VehicleRequestDTO {
    private String licensePlate;
    private VehicleType vehicleType;
    private String ownerName;

    public VehicleRequestDTO() {}

    public VehicleRequestDTO(String licensePlate, VehicleType vehicleType, String ownerName) {
        this.licensePlate = licensePlate;
        this.vehicleType  = vehicleType;
        this.ownerName    = ownerName;
    }

    // getters & setters
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}