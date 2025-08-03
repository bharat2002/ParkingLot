package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.ParkingLotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ParkingLotResponseDTO;
import com.bharatp.ParkingLotBackend.entity.ParkingLot;

import java.util.stream.Collectors;

public class ParkingLotMapper {

    public static ParkingLot toEntity(ParkingLotRequestDTO dto) {
        return new ParkingLot(dto.getName(), dto.getAddress());
    }

    public static ParkingLotResponseDTO toDTO(ParkingLot entity) {
        return new ParkingLotResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getCreatedAt(),
                entity.getFloors().stream()
                        .map(Floor::getId)
                        .collect(Collectors.toList())
        );
    }
}