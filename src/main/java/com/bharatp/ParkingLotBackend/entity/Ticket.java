package com.bharatp.ParkingLotBackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean active = true;
    private double fee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", nullable = false)
    private ParkingSpot spot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_panel_id", nullable = false)
    private EntryPanel entryPanel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exit_panel_id")
    private ExitPanel exitPanel;

    public Ticket() {}

    public Ticket(ParkingSpot spot, Vehicle vehicle, EntryPanel entryPanel) {
        this.spot = spot;
        this.vehicle = vehicle;
        this.entryPanel = entryPanel;
        this.entryTime = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() { return id; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public boolean isActive() { return active; }
    public double getFee() { return fee; }

    public ParkingSpot getSpot() { return spot; }
    public Vehicle getVehicle() { return vehicle; }
    public EntryPanel getEntryPanel() { return entryPanel; }
    public ExitPanel getExitPanel() { return exitPanel; }

    public void markExit(ExitPanel exitPanel, double fee) {
        this.exitTime = LocalDateTime.now();
        this.exitPanel = exitPanel;
        this.fee = fee;
        this.active = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setEntryPanel(EntryPanel entryPanel) {
        this.entryPanel = entryPanel;
    }

    public void setExitPanel(ExitPanel exitPanel) {
        this.exitPanel = exitPanel;
    }
}