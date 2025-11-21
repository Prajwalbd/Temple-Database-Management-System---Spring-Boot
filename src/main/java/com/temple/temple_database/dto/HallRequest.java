package com.temple.temple_database.dto;

import jakarta.validation.constraints.NotBlank;

public class HallRequest {
    
    @NotBlank(message = "Hall name is required")
    private String hallName;
    
    private String hallDetails;
    
    public HallRequest() {
    }
    
    public String getHallName() {
        return hallName;
    }
    
    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
    
    public String getHallDetails() {
        return hallDetails;
    }
    
    public void setHallDetails(String hallDetails) {
        this.hallDetails = hallDetails;
    }
}




