package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.SpotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.SpotResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Floor;
import com.bharatp.ParkingLotBackend.entity.ParkingSpot;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.SpotMapper;
import com.bharatp.ParkingLotBackend.repository.FloorRepository;
import com.bharatp.ParkingLotBackend.repository.ParkingSpotRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.SpotService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotServiceImpl implements SpotService {

    private final ParkingSpotRepository spotRepo;
    private final FloorRepository floorRepo;

    public SpotServiceImpl(ParkingSpotRepository spotRepo, FloorRepository floorRepo) {
        this.spotRepo = spotRepo;
        this.floorRepo = floorRepo;
    }

    @Override
    public SpotResponseDTO create(SpotRequestDTO dto) {
        Floor floor = floorRepo.findById(dto.getFloorId())
                .orElseThrow(() -> new NotFoundException("Floor not found: " + dto.getFloorId()));
        ParkingSpot saved = spotRepo.save(SpotMapper.toEntity(dto, floor));
        return SpotMapper.toDTO(saved);
    }

    @Override
    public List<SpotResponseDTO> getAllByFloor(Long floorId) {
        if (!floorRepo.existsById(floorId)) {
            throw new NotFoundException("Floor not found: " + floorId);
        }
        return spotRepo.findByFloorId(floorId).stream()
                .map(SpotMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpotResponseDTO> getAvailableByFloor(Long floorId) {
        if (!floorRepo.existsById(floorId)) {
            throw new NotFoundException("Floor not found: " + floorId);
        }
        return spotRepo.findByFloorIdAndIsAvailableTrue(floorId).stream()
                .map(SpotMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpotResponseDTO getById(Long id) {
        ParkingSpot spot = spotRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Spot not found: " + id));
        return SpotMapper.toDTO(spot);
    }

    @Override
    public void delete(Long id) {
        if (!spotRepo.existsById(id)) {
            throw new NotFoundException("Spot not found: " + id);
        }
        spotRepo.deleteById(id);
    }
}