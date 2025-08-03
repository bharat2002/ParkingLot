package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.PaymentRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.PaymentResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Payment;
import com.bharatp.ParkingLotBackend.entity.Ticket;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO dto, Ticket ticket, double amount) {
        Payment p = new Payment(ticket, amount, dto.getMode());
        return p;
    }

    public static PaymentResponseDTO toDTO(Payment p) {
        return new PaymentResponseDTO(
                p.getId(),
                p.getTicket().getId(),
                p.getAmount(),
                p.getMode(),
                p.getStatus(),
                p.getPaymentTime()
        );
    }
}