package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.EntryPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.EntryPanelResponseDTO;
import com.bharatp.ParkingLotBackend.entity.EntryPanel;
import com.bharatp.ParkingLotBackend.entity.ParkingLot;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.EntryPanelMapper;
import com.bharatp.ParkingLotBackend.repository.EntryPanelRepository;
import com.bharatp.ParkingLotBackend.repository.ParkingLotRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.EntryPanelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryPanelServiceImpl implements EntryPanelService {

    private final EntryPanelRepository repo;
    private final ParkingLotRepository lotRepo;

    public EntryPanelServiceImpl(EntryPanelRepository repo, ParkingLotRepository lotRepo) {
        this.repo    = repo;
        this.lotRepo = lotRepo;
    }

    @Override
    public EntryPanelResponseDTO create(EntryPanelRequestDTO dto) {
        ParkingLot lot = lotRepo.findById(dto.getParkingLotId())
                .orElseThrow(() -> new NotFoundException("ParkingLot not found: " + dto.getParkingLotId()));
        EntryPanel panel = EntryPanelMapper.toEntity(dto);
        panel.setParkingLot(lot);
        EntryPanel saved = repo.save(panel);
        return EntryPanelMapper.toDTO(saved);
    }

    @Override
    public List<EntryPanelResponseDTO> getByParkingLot(Long parkingLotId) {
        if (!lotRepo.existsById(parkingLotId)) {
            throw new NotFoundException("ParkingLot not found: " + parkingLotId);
        }
        return repo.findByParkingLotId(parkingLotId).stream()
                .map(EntryPanelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntryPanelResponseDTO getById(Long id) {
        EntryPanel e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("EntryPanel not found: " + id));
        return EntryPanelMapper.toDTO(e);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("EntryPanel not found: " + id);
        }
        repo.deleteById(id);
    }
}
