package com.bharatp.ParkingLotBackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingLot extends EntityBase{

    private String name;
    private String address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Floor> floors = new ArrayList<>();

    public ParkingLot() {
        setCreatedAt(LocalDateTime.now());
    }

    public ParkingLot(String name, String address) {
        this.name = name;
        this.address = address;
        setCreatedAt(LocalDateTime.now());
    }

    void AddFloor(Floor floor)
    {
        floors.add(floor);
    }

    void removeFloor(Floor floor)
    {
        floors.remove(floor);
    }
    //Getter Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
