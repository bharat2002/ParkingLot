package com.bharatp.ParkingLotBackend.repository;


import com.bharatp.ParkingLotBackend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByVehicleIdAndActiveTrue(Long vehicleId);
    List<Ticket> findByActiveTrue();
}