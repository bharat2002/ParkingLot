package com.bharatp.ParkingLotBackend.mapper;


import com.bharatp.ParkingLotBackend.dto.request.VehicleRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.VehicleResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Vehicle;

public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequestDTO dto) {
        return new Vehicle(
                dto.getLicensePlate(),
                dto.getVehicleType(),
                dto.getOwnerName()
        );
    }

    public static VehicleResponseDTO toDTO(Vehicle entity) {
        return new VehicleResponseDTO(
                entity.getId(),
                entity.getLicensePlate(),
                entity.getVehicleType(),
                entity.getOwnerName()
        );
    }
}