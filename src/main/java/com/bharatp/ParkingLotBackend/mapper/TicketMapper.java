package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.response.TicketResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Ticket;

public class TicketMapper {

    public static TicketResponseDTO toDTO(Ticket t) {
        return new TicketResponseDTO(
                t.getId(),
                t.getEntryTime(),
                t.getExitTime(),
                t.isActive(),
                t.getFee(),
                t.getSpot().getId(),
                t.getVehicle().getId()
        );
    }
}