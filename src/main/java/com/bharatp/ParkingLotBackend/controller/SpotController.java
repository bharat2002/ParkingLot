package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.SpotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.SpotResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.SpotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spots")
public class SpotController {

    private final SpotService service;

    public SpotController(SpotService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpotResponseDTO create(@RequestBody SpotRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/by-floor/{floorId}")
    public List<SpotResponseDTO> getAllByFloor(@PathVariable Long floorId) {
        return service.getAllByFloor(floorId);
    }

    @GetMapping("/available/{floorId}")
    public List<SpotResponseDTO> getAvailable(@PathVariable Long floorId) {
        return service.getAvailableByFloor(floorId);
    }

    @GetMapping("/{id}")
    public SpotResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}