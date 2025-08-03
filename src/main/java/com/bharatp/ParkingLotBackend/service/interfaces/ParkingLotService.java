package com.bharatp.ParkingLotBackend.service.interfaces ;

import com.bharatp.ParkingLotBackend.dto.request.ParkingLotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ParkingLotResponseDTO;

import java.util.List;

public interface ParkingLotService {
    ParkingLotResponseDTO create(ParkingLotRequestDTO dto);
    List<ParkingLotResponseDTO> getAll();
    ParkingLotResponseDTO getById(Long id);
    void delete(Long id);
}