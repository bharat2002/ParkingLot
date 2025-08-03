package com.bharatp.ParkingLotBackend.controller;

import com.bharatp.ParkingLotBackend.dto.request.VehicleRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.VehicleResponseDTO;
import com.bharatp.ParkingLotBackend.service.interfaces.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponseDTO register(@RequestBody VehicleRequestDTO dto) {
        return service.register(dto);
    }

    @GetMapping("/{id}")
    public VehicleResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-plate/{licensePlate}")
    public VehicleResponseDTO getByPlate(@PathVariable String licensePlate) {
        return service.getByPlate(licensePlate);
    }

    @GetMapping
    public List<VehicleResponseDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}