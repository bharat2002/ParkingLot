package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.PaymentRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.PaymentResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Payment;
import com.bharatp.ParkingLotBackend.entity.Ticket;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.PaymentMapper;
import com.bharatp.ParkingLotBackend.repository.PaymentRepository;
import com.bharatp.ParkingLotBackend.repository.TicketRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository payRepo;
    private final TicketRepository   ticketRepo;

    public PaymentServiceImpl(PaymentRepository payRepo,
                              TicketRepository ticketRepo) {
        this.payRepo    = payRepo;
        this.ticketRepo = ticketRepo;
    }

    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {
        Ticket ticket = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + dto.getTicketId()));

        double amount = ticket.getFee();  // use calculated fee
        Payment payment = PaymentMapper.toEntity(dto, ticket, amount);
        Payment saved = payRepo.save(payment);
        return PaymentMapper.toDTO(saved);
    }

    @Override
    public PaymentResponseDTO getByTicket(Long ticketId) {
        Payment p = payRepo.findByTicketId(ticketId)
                .orElseThrow(() -> new NotFoundException("Payment not found for ticket: " + ticketId));
        return PaymentMapper.toDTO(p);
    }
}