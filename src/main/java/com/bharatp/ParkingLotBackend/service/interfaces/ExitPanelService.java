package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.ExitPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ExitPanelResponseDTO;

import java.util.List;

public interface ExitPanelService {
    ExitPanelResponseDTO create(ExitPanelRequestDTO dto);
    List<ExitPanelResponseDTO> getByParkingLot(Long parkingLotId);
    ExitPanelResponseDTO getById(Long id);
    void delete(Long id);
}
