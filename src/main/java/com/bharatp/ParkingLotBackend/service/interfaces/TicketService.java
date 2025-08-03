package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.TicketRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketExitRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketResponseDTO;

import java.util.List;

public interface TicketService {
    TicketResponseDTO issueTicket(TicketRequestDTO dto);
    TicketResponseDTO exitTicket(TicketExitRequestDTO dto);
    List<TicketResponseDTO> getActiveTickets();
    TicketResponseDTO getById(Long id);
}