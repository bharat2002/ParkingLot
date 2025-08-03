package com.bharatp.ParkingLotBackend.entity;

import com.bharatp.ParkingLotBackend.enums.SpotType;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotNumber;

    @Enumerated(EnumType.STRING)
    private SpotType spotType;

    private boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    public ParkingSpot() {}

    public ParkingSpot(String spotNumber, SpotType spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
    }

    // getters & setters

    public Long getId() { return id; }

    public String getSpotNumber() { return spotNumber; }
    public void setSpotNumber(String spotNumber) { this.spotNumber = spotNumber; }

    public SpotType getSpotType() { return spotType; }
    public void setSpotType(SpotType spotType) { this.spotType = spotType; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public Floor getFloor() { return floor; }
    public void setFloor(Floor floor) { this.floor = floor; }
}
