package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.FloorRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.FloorResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.FloorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService service;

    public FloorController(FloorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FloorResponseDTO create(@RequestBody FloorRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/by-lot/{parkingLotId}")
    public List<FloorResponseDTO> getAllByLot(@PathVariable Long parkingLotId) {
        return service.getAllByParkingLot(parkingLotId);
    }

    @GetMapping("/{id}")
    public FloorResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}