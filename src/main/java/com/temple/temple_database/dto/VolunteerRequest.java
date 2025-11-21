package com.temple.temple_database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class VolunteerRequest {
    
    @NotBlank(message = "Volunteer name is required")
    private String volunteerName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String volunteerPhone;
    
    private String volunteerDetail;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    public VolunteerRequest() {
    }
    
    public String getVolunteerName() {
        return volunteerName;
    }
    
    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
    
    public String getVolunteerPhone() {
        return volunteerPhone;
    }
    
    public void setVolunteerPhone(String volunteerPhone) {
        this.volunteerPhone = volunteerPhone;
    }
    
    public String getVolunteerDetail() {
        return volunteerDetail;
    }
    
    public void setVolunteerDetail(String volunteerDetail) {
        this.volunteerDetail = volunteerDetail;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
}




