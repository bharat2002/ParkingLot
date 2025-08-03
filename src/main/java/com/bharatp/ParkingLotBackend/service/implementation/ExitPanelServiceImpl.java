package com.bharatp.ParkingLotBackend.service.implementation;

import com.bharatp.ParkingLotBackend.dto.request.ExitPanelRequestDTO;
import com.bharatp.ParkingLotBackend.dto.response.ExitPanelResponseDTO;
import com.bharatp.ParkingLotBackend.entity.ExitPanel;
import com.bharatp.ParkingLotBackend.entity.ParkingLot;
import com.bharatp.ParkingLotBackend.exception.NotFoundException;
import com.bharatp.ParkingLotBackend.mapper.ExitPanelMapper;
import com.bharatp.ParkingLotBackend.repository.ExitPanelRepository;
import com.bharatp.ParkingLotBackend.repository.ParkingLotRepository;
import com.bharatp.ParkingLotBackend.service.interfaces.ExitPanelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExitPanelServiceImpl implements ExitPanelService {

    private final ExitPanelRepository repo;
    private final ParkingLotRepository lotRepo;

    public ExitPanelServiceImpl(ExitPanelRepository repo, ParkingLotRepository lotRepo) {
        this.repo    = repo;
        this.lotRepo = lotRepo;
    }

    @Override
    public ExitPanelResponseDTO create(ExitPanelRequestDTO dto) {
        ParkingLot lot = lotRepo.findById(dto.getParkingLotId())
                .orElseThrow(() -> new NotFoundException("ParkingLot not found: " + dto.getParkingLotId()));
        ExitPanel panel = ExitPanelMapper.toEntity(dto);
        panel.setParkingLot(lot);
        ExitPanel saved = repo.save(panel);
        return ExitPanelMapper.toDTO(saved);
    }

    @Override
    public List<ExitPanelResponseDTO> getByParkingLot(Long parkingLotId) {
        if (!lotRepo.existsById(parkingLotId)) {
            throw new NotFoundException("ParkingLot not found: " + parkingLotId);
        }
        return repo.findByParkingLotId(parkingLotId).stream()
                .map(ExitPanelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExitPanelResponseDTO getById(Long id) {
        ExitPanel e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("ExitPanel not found: " + id));
        return ExitPanelMapper.toDTO(e);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("ExitPanel not found: " + id);
        }
        repo.deleteById(id);
    }
}
