package com.bharatp.ParkingLotBackend.repository;

import com.bharatp.ParkingLotBackend.entity.ExitPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExitPanelRepository extends JpaRepository<ExitPanel, Long> {
    List<ExitPanel> findByParkingLotId(Long parkingLotId);
}