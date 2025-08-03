package com.bharatp.ParkingLotBackend.service.allocate;


import com.bharatp.ParkingLotBackend.entity.Floor;
import com.bharatp.ParkingLotBackend.entity.ParkingSpot;
import com.bharatp.ParkingLotBackend.enums.VehicleType;

import java.util.Optional;

public interface SpotAllocationStrategy {
    /**
     * Find and reserve one available spot on this floor for the given vehicle type.
     * Must mark the spot unavailable before returning.
     */
    Optional<ParkingSpot> allocateSpot(Floor floor, VehicleType vehicleType);
}