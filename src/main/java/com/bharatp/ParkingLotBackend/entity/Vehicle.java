package com.bharatp.ParkingLotBackend.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Vehicle extends EntityBase{
    @Column( nullable = false)

    private String RegistrationNumber;
}
