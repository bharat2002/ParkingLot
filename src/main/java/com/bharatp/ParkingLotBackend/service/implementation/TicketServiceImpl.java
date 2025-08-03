package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.TicketRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketExitRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.TicketResponseDTO;
import com.bharatp.ParkingLotBackend.entity.*;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.TicketMapper;
import com.bharatp.ParkingLotBackend.repository.*;
import com.bharatp.ParkingLotBackend.service.allocate.PricingPolicy;
import com.bharatp.ParkingLotBackend.service.allocate.SpotAllocationStrategy;
import com.bharatp.ParkingLotBackend.service.interfaces.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepo;
    private final VehicleRepository vehicleRepo;
    private final FloorRepository floorRepo;
    private final ParkingSpotRepository spotRepo;
    private final EntryPanelRepository entryPanelRepo;
    private final ExitPanelRepository exitPanelRepo;
    private final SpotAllocationStrategy allocator;
    private final PricingPolicy pricingPolicy;

    public TicketServiceImpl(TicketRepository ticketRepo,
                             VehicleRepository vehicleRepo,
                             FloorRepository floorRepo,
                             ParkingSpotRepository spotRepo,
                             EntryPanelRepository entryPanelRepo,
                             ExitPanelRepository exitPanelRepo, SpotAllocationStrategy allocator, PricingPolicy pricingPolicy) {
        this.ticketRepo = ticketRepo;
        this.vehicleRepo = vehicleRepo;
        this.floorRepo = floorRepo;
        this.spotRepo = spotRepo;
        this.entryPanelRepo = entryPanelRepo;
        this.exitPanelRepo = exitPanelRepo;
        this.allocator = allocator;
        this.pricingPolicy = pricingPolicy;
    }

    @Override
    @Transactional
    public TicketResponseDTO issueTicket(TicketRequestDTO dto) {
        Vehicle v = vehicleRepo.findById(dto.getVehicleId()).orElseThrow(
        ()->{
            return new NotFoundException("Vehicle Not Found With Id : "+ dto.getVehicleId());
        });
        Floor f   = floorRepo.findById(dto.getFloorId()).orElseThrow(()->{
            return  new NotFoundException("Floor Not Found with Id :"+ dto.getFloorId() );
        });
        ParkingSpot spot = allocator.allocateSpot(f, v.getVehicleType())
                .orElseThrow(() -> new NotFoundException("No spot available"));
        EntryPanel panel = entryPanelRepo.findById(dto.getEntryPanelId()).orElseThrow(
                ()->{
                    return new NotFoundException("No Entry Panel Found with Id : "+dto.getEntryPanelId());
                }
        );

        Ticket t = new Ticket(spot, v, panel);
        return TicketMapper.toDTO(ticketRepo.save(t));
    }

    @Override
    @Transactional
    public TicketResponseDTO exitTicket(TicketExitRequestDTO dto) {
        Ticket ticket = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + dto.getTicketId()));
        if (!ticket.isActive()) {
            throw new IllegalStateException("Ticket already exited: " + dto.getTicketId());
        }
        ExitPanel exitPanel = exitPanelRepo.findById(dto.getExitPanelId())
                .orElseThrow(() -> new NotFoundException("ExitPanel not found: " + dto.getExitPanelId()));


        ticket.markExit(exitPanel, 0);    // temporarily set fee=0, exitTime=now

        double fee = pricingPolicy.calculateFee(ticket);
        ticket.setFee(fee);
        ticket.setActive(false);
        ticket.getSpot().setAvailable(true);
        spotRepo.save(ticket.getSpot());
        return TicketMapper.toDTO(ticketRepo.save(ticket));

    }

    @Override
    public List<TicketResponseDTO> getActiveTickets() {
        return ticketRepo.findByActiveTrue().stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponseDTO getById(Long id) {
        return ticketRepo.findById(id)
                .map(TicketMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + id));
    }
}