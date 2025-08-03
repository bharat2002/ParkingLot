package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.entity.Floor;
import com.bharatp.ParkingLotBackend.entity.ParkingSpot;
import com.bharatp.ParkingLotBackend.enums.VehicleType;
import com.bharatp.ParkingLotBackend.repository.ParkingSpotRepository;
import com.bharatp.ParkingLotBackend.service.allocate.SpotAllocationStrategy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FirstFitSpotAllocator implements SpotAllocationStrategy {
    private final ParkingSpotRepository spotRepo;

    public FirstFitSpotAllocator(ParkingSpotRepository spotRepo) {
        this.spotRepo = spotRepo;
    }

    @Override
    @Transactional
    public Optional<ParkingSpot> allocateSpot(Floor floor, VehicleType vehicleType) {
        return spotRepo.findByFloorIdAndIsAvailableTrue(floor.getId()).stream()
                .filter(spot -> spot.getSpotType().matchesVehicle(vehicleType))
                .findFirst()
                .map(spot -> {
                    spot.setAvailable(false);
                    return spotRepo.save(spot);
                });
    }
}
