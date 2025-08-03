package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.SpotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.SpotResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Floor;
import com.bharatp.ParkingLotBackend.entity.ParkingSpot;

public class SpotMapper {

    public static ParkingSpot toEntity(SpotRequestDTO dto, Floor floor) {
        ParkingSpot spot = new ParkingSpot(dto.getSpotNumber(), dto.getSpotType());
        spot.setFloor(floor);
        return spot;
    }

    public static SpotResponseDTO toDTO(ParkingSpot entity) {
        return new SpotResponseDTO(
                entity.getId(),
                entity.getSpotNumber(),
                entity.getSpotType(),
                entity.isAvailable(),
                entity.getFloor().getId()
        );
    }
}