package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.ParkingLotRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ParkingLotResponseDTO;
import com.bharatp.ParkingLotBackend.entity.ParkingLot;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.ParkingLotMapper;
import com.bharatp.ParkingLotBackend.repository.ParkingLotRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.ParkingLotService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository repo;

    public ParkingLotServiceImpl(ParkingLotRepository repo) {
        this.repo = repo;
    }

    @Override
    public ParkingLotResponseDTO create(ParkingLotRequestDTO dto) {
        ParkingLot saved = repo.save(ParkingLotMapper.toEntity(dto));
        return ParkingLotMapper.toDTO(saved);
    }

    @Override
    public List<ParkingLotResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(ParkingLotMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingLotResponseDTO getById(Long id) {
        ParkingLot lot = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("ParkingLot not found: " + id));
        return ParkingLotMapper.toDTO(lot);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("ParkingLot not found: " + id);
        }
        repo.deleteById(id);
    }
}