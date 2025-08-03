package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.FloorRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.FloorResponseDTO;
import com.bharatp.ParkingLotBackend.entity.Floor;
import com.bharatp.ParkingLotBackend.entity.ParkingLot;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.FloorMapper;
import com.bharatp.ParkingLotBackend.repository.FloorRepository;
import com.bharatp.ParkingLotBackend.repository.ParkingLotRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.FloorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepo;
    private final ParkingLotRepository lotRepo;

    public FloorServiceImpl(FloorRepository floorRepo, ParkingLotRepository lotRepo) {
        this.floorRepo = floorRepo;
        this.lotRepo = lotRepo;
    }

    @Override
    public FloorResponseDTO create(FloorRequestDTO dto) {
        ParkingLot lot = lotRepo.findById(dto.getParkingLotId())
                .orElseThrow(() -> new NotFoundException("ParkingLot not found: " + dto.getParkingLotId()));
        Floor floor = FloorMapper.toEntity(dto);
        floor.setParkingLot(lot);
        Floor saved = floorRepo.save(floor);
        return FloorMapper.toDTO(saved);
    }

    @Override
    public List<FloorResponseDTO> getAllByParkingLot(Long parkingLotId) {
        if (!lotRepo.existsById(parkingLotId)) {
            throw new NotFoundException("ParkingLot not found: " + parkingLotId);
        }
        return floorRepo.findByParkingLotId(parkingLotId).stream()
                .map(FloorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FloorResponseDTO getById(Long id) {
        Floor floor = floorRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Floor not found: " + id));
        return FloorMapper.toDTO(floor);
    }

    @Override
    public void delete(Long id) {
        if (!floorRepo.existsById(id)) {
            throw new NotFoundException("Floor not found: " + id);
        }
        floorRepo.deleteById(id);
    }
}