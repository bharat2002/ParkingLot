package com.bharatp.ParkingLotBackend.dto.request;

public class ExitPanelRequestDTO {
    private Long parkingLotId;
    private String panelCode;
    private String location;

    public ExitPanelRequestDTO() {}

    public ExitPanelRequestDTO(Long parkingLotId, String panelCode, String location) {
        this.parkingLotId = parkingLotId;
        this.panelCode    = panelCode;
        this.location     = location;
    }

    // getters & setters
    public Long getParkingLotId() { return parkingLotId; }
    public void setParkingLotId(Long parkingLotId) { this.parkingLotId = parkingLotId; }
    public String getPanelCode() { return panelCode; }
    public void setPanelCode(String panelCode) { this.panelCode = panelCode; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
