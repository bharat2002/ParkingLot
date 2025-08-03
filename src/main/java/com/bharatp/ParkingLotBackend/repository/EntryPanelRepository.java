package com.bharatp.ParkingLotBackend.repository;

import com.bharatp.ParkingLotBackend.entity.EntryPanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryPanelRepository extends JpaRepository<EntryPanel, Long> {
    List<EntryPanel> findByParkingLotId(Long parkingLotId);
}