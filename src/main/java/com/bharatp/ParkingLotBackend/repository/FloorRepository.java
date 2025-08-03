package com.bharatp.ParkingLotBackend.repository;

import com.bharatp.ParkingLotBackend.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findByParkingLotId(Long parkingLotId);
}