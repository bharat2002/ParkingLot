package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.PaymentRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO makePayment(PaymentRequestDTO dto);
    PaymentResponseDTO getByTicket(Long ticketId);
}