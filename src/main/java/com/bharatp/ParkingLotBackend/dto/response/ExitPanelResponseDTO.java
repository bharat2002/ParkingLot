package com.bharatp.ParkingLotBackend.dto.response;

public class ExitPanelResponseDTO {
    private Long id;
    private String panelCode;
    private String location;
    private Long parkingLotId;

    public ExitPanelResponseDTO() {}

    public ExitPanelResponseDTO(Long id, String panelCode, String location, Long parkingLotId) {
        this.id           = id;
        this.panelCode    = panelCode;
        this.location     = location;
        this.parkingLotId = parkingLotId;
    }

    // getters
    public Long getId() { return id; }
    public String getPanelCode() { return panelCode; }
    public String getLocation() { return location; }
    public Long getParkingLotId() { return parkingLotId; }
}