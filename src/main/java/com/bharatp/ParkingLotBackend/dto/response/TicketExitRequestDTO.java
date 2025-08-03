package com.bharatp.ParkingLotBackend.dto.response;

public class TicketExitRequestDTO {
    private Long ticketId;
    private Long exitPanelId;

    public TicketExitRequestDTO() {}
    public TicketExitRequestDTO(Long ticketId, Long exitPanelId) {
        this.ticketId = ticketId;
        this.exitPanelId = exitPanelId;
    }

    // getters & setters
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public Long getExitPanelId() { return exitPanelId; }
    public void setExitPanelId(Long exitPanelId) { this.exitPanelId = exitPanelId; }
}