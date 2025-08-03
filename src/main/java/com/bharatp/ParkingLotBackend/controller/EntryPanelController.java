package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.EntryPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.EntryPanelResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.EntryPanelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entry-panels")
public class EntryPanelController {

    private final EntryPanelService service;

    public EntryPanelController(EntryPanelService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntryPanelResponseDTO create(@RequestBody EntryPanelRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/by-lot/{parkingLotId}")
    public List<EntryPanelResponseDTO> getByLot(@PathVariable Long parkingLotId) {
        return service.getByParkingLot(parkingLotId);
    }

    @GetMapping("/{id}")
    public EntryPanelResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}