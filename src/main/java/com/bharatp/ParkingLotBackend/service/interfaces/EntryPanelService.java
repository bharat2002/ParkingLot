package com.bharatp.ParkingLotBackend.service.interfaces;

import com.bharatp.ParkingLotBackend.dto.request.EntryPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.EntryPanelResponseDTO;

import java.util.List;

public interface EntryPanelService {
    EntryPanelResponseDTO create(EntryPanelRequestDTO dto);
    List<EntryPanelResponseDTO> getByParkingLot(Long parkingLotId);
    EntryPanelResponseDTO getById(Long id);
    void delete(Long id);
}