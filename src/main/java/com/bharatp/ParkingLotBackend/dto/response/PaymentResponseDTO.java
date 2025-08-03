package com.bharatp.ParkingLotBackend.dto.response;

import com.bharatp.ParkingLotBackend.enums.PaymentMode;
import com.bharatp.ParkingLotBackend.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponseDTO {
    private Long id;
    private Long ticketId;
    private double amount;
    private PaymentMode mode;
    private PaymentStatus status;
    private LocalDateTime paymentTime;

    public PaymentResponseDTO(Long id, Long ticketId, double amount, PaymentMode mode, PaymentStatus status, LocalDateTime paymentTime) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
        this.mode = mode;
        this.status = status;
        this.paymentTime = paymentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public void setMode(PaymentMode mode) {
        this.mode = mode;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }
// constructors, getters...
}