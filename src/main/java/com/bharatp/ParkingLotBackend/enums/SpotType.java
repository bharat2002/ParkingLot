package com.bharatp.ParkingLotBackend.enums;

public enum SpotType {
    TWO_WHEELER,
    CAR,
    LARGE_VEHICLE,
    ELECTRIC;

    public boolean matchesVehicle(VehicleType vt) {
        return this.name().equals(vt.name())
                || (this==LARGE_VEHICLE && (vt==VehicleType.SUV||vt==VehicleType.TRUCK));
    }
}