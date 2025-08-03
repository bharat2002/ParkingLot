package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.PaymentRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.PaymentResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO pay(@RequestBody PaymentRequestDTO dto) {
        return service.makePayment(dto);
    }

    @GetMapping("/by-ticket/{ticketId}")
    public PaymentResponseDTO getByTicket(@PathVariable Long ticketId) {
        return service.getByTicket(ticketId);
    }
}
