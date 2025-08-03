package com.bharatp.ParkingLotBackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exit_panel")
public class ExitPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String panelCode;

    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

    public ExitPanel() {}

    public ExitPanel(String panelCode, String location) {
        this.panelCode = panelCode;
        this.location  = location;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getPanelCode() { return panelCode; }
    public void setPanelCode(String panelCode) { this.panelCode = panelCode; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public ParkingLot getParkingLot() { return parkingLot; }
    public void setParkingLot(ParkingLot parkingLot) { this.parkingLot = parkingLot; }
}