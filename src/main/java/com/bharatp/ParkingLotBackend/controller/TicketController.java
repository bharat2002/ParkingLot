package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.TicketRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketExitRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponseDTO issue(@RequestBody TicketRequestDTO dto) {
        return service.issueTicket(dto);
    }

    @PostMapping("/exit")
    public TicketResponseDTO exit(@RequestBody TicketExitRequestDTO dto) {
        return service.exitTicket(dto);
    }

    @GetMapping("/active")
    public List<TicketResponseDTO> active() {
        return service.getActiveTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
}