package com.bharatp.ParkingLotBackend.dto.request;

public class TicketRequestDTO {
    private Long vehicleId;
    private Long floorId;
    private Long entryPanelId;

    public TicketRequestDTO() {}
    public TicketRequestDTO(Long vehicleId, Long floorId, Long entryPanelId) {
        this.vehicleId = vehicleId;
        this.floorId = floorId;
        this.entryPanelId = entryPanelId;
    }

    // getters & setters
    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public Long getFloorId() { return floorId; }
    public void setFloorId(Long floorId) { this.floorId = floorId; }
    public Long getEntryPanelId() { return entryPanelId; }
    public void setEntryPanelId(Long entryPanelId) { this.entryPanelId = entryPanelId; }
}