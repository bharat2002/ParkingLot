package com.bharatp.ParkingLotBackend.dto.response;

import com.bharatp.ParkingLotBackend.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponseDTO {
    private Long id;
    private Long ticketId;
    private double amount;
    private PaymentMode mode;
    private PaymentStatus status;
    private LocalDateTime paymentTime;

    // constructors, getters...
}