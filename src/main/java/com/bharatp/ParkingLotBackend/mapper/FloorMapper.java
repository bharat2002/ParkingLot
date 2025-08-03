package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.FloorRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.FloorResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Floor;

import java.util.stream.Collectors;

public class FloorMapper {

    public static Floor toEntity(FloorRequestDTO dto) {
        Floor floor = new Floor(dto.getFloorNumber());
        return floor;
    }

    public static FloorResponseDTO toDTO(Floor entity) {
        return new FloorResponseDTO(
                entity.getId(),
                entity.getFloorNumber(),
                entity.getParkingLot().getId(),
                entity.getSpots().stream()
                        .map(spot -> spot.getId())
                        .collect(Collectors.toList())
        );
    }
}