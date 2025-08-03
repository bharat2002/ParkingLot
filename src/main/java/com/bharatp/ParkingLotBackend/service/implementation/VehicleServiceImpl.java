package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.VehicleRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.VehicleResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Vehicle;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.VehicleMapper;
import com.bharatp.ParkingLotBackend.repository.VehicleRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repo;

    public VehicleServiceImpl(VehicleRepository repo) {
        this.repo = repo;
    }

    @Override
    public VehicleResponseDTO register(VehicleRequestDTO dto) {
        Vehicle saved = repo.save(VehicleMapper.toEntity(dto));
        return VehicleMapper.toDTO(saved);
    }

    @Override
    public VehicleResponseDTO getById(Long id) {
        Vehicle v = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found: " + id));
        return VehicleMapper.toDTO(v);
    }

    @Override
    public VehicleResponseDTO getByPlate(String licensePlate) {
        Vehicle v = repo.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new NotFoundException("Vehicle not found: " + licensePlate));
        return VehicleMapper.toDTO(v);
    }

    @Override
    public List<VehicleResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Vehicle not found: " + id);
        }
        repo.deleteById(id);
    }
}