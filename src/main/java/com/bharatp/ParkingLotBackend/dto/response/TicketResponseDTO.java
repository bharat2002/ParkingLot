package com.bharatp.ParkingLotBackend.dto.response;


import java.time.LocalDateTime;

public class TicketResponseDTO {
    private Long id;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean active;
    private double fee;
    private Long spotId;
    private Long vehicleId;

    public TicketResponseDTO() {}
    public TicketResponseDTO(Long id, LocalDateTime entryTime, LocalDateTime exitTime,
                             boolean active, double fee, Long spotId, Long vehicleId) {
        this.id = id;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.active = active;
        this.fee = fee;
        this.spotId = spotId;
        this.vehicleId = vehicleId;
    }

    // getters
    public Long getId() { return id; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public boolean isActive() { return active; }
    public double getFee() { return fee; }
    public Long getSpotId() { return spotId; }
    public Long getVehicleId() { return vehicleId; }
}