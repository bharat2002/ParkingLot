package com.bharatp.ParkingLotBackend.entity;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Ticket extends EntityBase{
    private Vehicle vehicle;
    private LocalDateTime EntryTime;
    private LocalDateTime ExitTime;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime, ParkingSpot parkingSpot) {
        this.vehicle = vehicle;
        EntryTime = entryTime;
        ExitTime = exitTime;
        this.parkingSpot = parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getEntryTime() {
        return EntryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        EntryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return ExitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        ExitTime = exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
