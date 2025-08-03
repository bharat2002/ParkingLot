package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.ParkingLotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ParkingLotResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkinglots")
public class ParkingLotController {

    private final ParkingLotService service;

    @Autowired
    public ParkingLotController(ParkingLotService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotResponseDTO create(@RequestBody ParkingLotRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ParkingLotResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ParkingLotResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}