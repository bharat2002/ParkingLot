package com.bharatp.ParkingLotBackend.dto.request;

import com.bharatp.ParkingLotBackend.enums.PaymentMode;

public class PaymentRequestDTO {
    private Long ticketId;
    private PaymentMode mode;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public void setMode(PaymentMode mode) {
        this.mode = mode;
    }
// getters/setters...
}