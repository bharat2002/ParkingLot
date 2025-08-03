package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.entity.Ticket;
import com.bharatp.ParkingLotBackend.service.allocate.PricingPolicy;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class HourlyPricingPolicy implements PricingPolicy {
    private static final double RATE_PER_HOUR = 20.0;  // ₹20 / hour

    @Override
    public double calculateFee(Ticket ticket) {
        long minutes = Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toMinutes();
        double hours = Math.ceil(minutes / 60.0);
        return hours * RATE_PER_HOUR;
    }
}