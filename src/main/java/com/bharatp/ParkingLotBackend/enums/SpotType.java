package com.bharatp.ParkingLotBackend.enums;

public enum SpotType {
    TWO_WHEELER,
    CAR,
    LARGE,      // e.g. SUV, Truck
    ELECTRIC;

    /** Matches this spot to the given vehicle type. */
    public boolean matchesVehicle(VehicleType vt) {
        switch (this) {
            case TWO_WHEELER:
                return vt == VehicleType.TWO_WHEELER;
            case CAR:
                return vt == VehicleType.CAR;
            case LARGE:
                return vt == VehicleType.SUV || vt == VehicleType.TRUCK;
            case ELECTRIC:
                // you might additionally check an isElectric flag on the vehicle
                return vt == VehicleType.CAR || vt == VehicleType.SUV;
            default:
                return false;
        }
    }
}