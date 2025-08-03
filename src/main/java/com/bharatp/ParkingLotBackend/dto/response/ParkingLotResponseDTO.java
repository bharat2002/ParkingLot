package com.bharatp.ParkingLotBackend.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotResponseDTO {
    private Long id;
    private String name;
    private String address;
    private LocalDateTime createdAt;
    private List<Long> floorIds;

    public ParkingLotResponseDTO() {}

    public ParkingLotResponseDTO(Long id, String name, String address,
                                 LocalDateTime createdAt, List<Long> floorIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdAt = createdAt;
        this.floorIds = floorIds;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Long> getFloorIds() { return floorIds; }
}