package com.bharatp.ParkingLotBackend.service.allocate;

import com.bharatp.ParkingLotBackend.entity.Ticket;

public interface PricingPolicy {
    double calculateFee(Ticket ticket);
}
