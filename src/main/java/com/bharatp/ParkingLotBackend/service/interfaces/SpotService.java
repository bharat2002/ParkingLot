package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.SpotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.SpotResponseDTO;

import java.util.List;

public interface SpotService {
    SpotResponseDTO create(SpotRequestDTO dto);
    List<SpotResponseDTO> getAllByFloor(Long floorId);
    List<SpotResponseDTO> getAvailableByFloor(Long floorId);
    SpotResponseDTO getById(Long id);
    void delete(Long id);
}