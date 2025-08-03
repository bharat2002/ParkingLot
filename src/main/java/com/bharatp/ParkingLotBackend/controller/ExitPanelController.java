package com.bharatp.ParkingLotBackend.controller;
import com.bharatp.ParkingLotBackend.dto.request.ExitPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ExitPanelResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.ExitPanelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exit-panels")
public class ExitPanelController {

    private final ExitPanelService service;

    public ExitPanelController(ExitPanelService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExitPanelResponseDTO create(@RequestBody ExitPanelRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/by-lot/{parkingLotId}")
    public List<ExitPanelResponseDTO> getByLot(@PathVariable Long parkingLotId) {
        return service.getByParkingLot(parkingLotId);
    }

    @GetMapping("/{id}")
    public ExitPanelResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}