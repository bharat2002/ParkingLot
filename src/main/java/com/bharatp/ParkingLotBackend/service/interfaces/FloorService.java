package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.FloorRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.FloorResponseDTO;

import java.util.List;

public interface FloorService {
    FloorResponseDTO create(FloorRequestDTO dto);
    List<FloorResponseDTO> getAllByParkingLot(Long parkingLotId);
    FloorResponseDTO getById(Long id);
    void delete(Long id);
}