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
    public TicketResponseDTO issueTicket(TicketRequestDTO dto) {
        Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId())
                .orElseThrow(() -> new NotFoundException("Vehicle not found: " + dto.getVehicleId()));
        Floor floor = floorRepo.findById(dto.getFloorId())
                .orElseThrow(() -> new NotFoundException("Floor not found: " + dto.getFloorId()));
        // simple allocation: first available spot matching vehicle type
        ParkingSpot spot = spotRepo.findByFloorIdAndIsAvailableTrue(floor.getId()).stream()
                .filter(s -> s.getSpotType().name().contains(vehicle.getVehicleType().name()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No spot available on floor: " + floor.getId()));

        spot.setAvailable(false);
        spotRepo.save(spot);

        EntryPanel panel = entryPanelRepo.findById(dto.getEntryPanelId())
                .orElseThrow(() -> new NotFoundException("EntryPanel not found: " + dto.getEntryPanelId()));

        Ticket ticket = new Ticket(spot, vehicle, panel);
        Ticket saved = ticketRepo.save(ticket);
        return TicketMapper.toDTO(saved);
    }

    @Override
    public TicketResponseDTO exitTicket(TicketExitRequestDTO dto) {
        Ticket ticket = ticketRepo.findById(dto.getTicketId())
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + dto.getTicketId()));
        if (!ticket.isActive()) {
            throw new IllegalStateException("Ticket already exited: " + dto.getTicketId());
        }
        ExitPanel exitPanel = exitPanelRepo.findById(dto.getExitPanelId())
                .orElseThrow(() -> new NotFoundException("ExitPanel not found: " + dto.getExitPanelId()));

        // calculate fee: e.g., $1 per minute
        long minutes = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toMinutes();
        double fee = minutes * 1.0;

        ticket.markExit(exitPanel, fee);
        ParkingSpot spot = ticket.getSpot();
        spot.setAvailable(true);
        spotRepo.save(spot);

        Ticket updated = ticketRepo.save(ticket);
        return TicketMapper.toDTO(updated);
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