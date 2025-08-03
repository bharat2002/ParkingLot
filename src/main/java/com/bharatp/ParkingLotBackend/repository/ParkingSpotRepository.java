package com.bharatp.ParkingLotBackend.repository;

import com.bharatp.ParkingLotBackend.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByFloorId(Long floorId);
    List<ParkingSpot> findByFloorIdAndIsAvailableTrue(Long floorId);
}