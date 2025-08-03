package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.EntryPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.EntryPanelResponseDTO;
import com.bharatp.ParkingLotBackend.entity.EntryPanel;

public class EntryPanelMapper {

    public static EntryPanel toEntity(EntryPanelRequestDTO dto) {
        return new EntryPanel(dto.getPanelCode(), dto.getLocation());
    }

    public static EntryPanelResponseDTO toDTO(EntryPanel e) {
        return new EntryPanelResponseDTO(
                e.getId(),
                e.getPanelCode(),
                e.getLocation(),
                e.getParkingLot().getId()
        );
    }
}