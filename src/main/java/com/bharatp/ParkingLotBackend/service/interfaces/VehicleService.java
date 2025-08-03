package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.VehicleRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {
    VehicleResponseDTO register(VehicleRequestDTO dto);
    VehicleResponseDTO getById(Long id);
    VehicleResponseDTO getByPlate(String licensePlate);
    List<VehicleResponseDTO> getAll();
    void delete(Long id);
}