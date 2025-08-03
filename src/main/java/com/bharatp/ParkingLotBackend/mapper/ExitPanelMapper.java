package com.bharatp.ParkingLotBackend.mapper;

import com.bharatp.ParkingLotBackend.dto.request.ExitPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ExitPanelResponseDTO;
import com.bharatp.ParkingLotBackend.entity.ExitPanel;

public class ExitPanelMapper {

    public static ExitPanel toEntity(ExitPanelRequestDTO dto) {
        return new ExitPanel(dto.getPanelCode(), dto.getLocation());
    }

    public static ExitPanelResponseDTO toDTO(ExitPanel e) {
        return new ExitPanelResponseDTO(
                e.getId(),
                e.getPanelCode(),
                e.getLocation(),
                e.getParkingLot().getId()
        );
    }
}
