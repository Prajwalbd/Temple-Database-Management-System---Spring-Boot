package com.temple.temple_database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class StaffRequest {
    
    @NotBlank(message = "Staff name is required")
    private String staffName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String staffPhone;
    
    private String staffDetails;
    
    @NotBlank(message = "Staff role is required")
    private String staffRole;
    
    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    private BigDecimal staffSalary;
    
    public StaffRequest() {
    }
    
    public String getStaffName() {
        return staffName;
    }
    
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    public String getStaffPhone() {
        return staffPhone;
    }
    
    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
    
    public String getStaffDetails() {
        return staffDetails;
    }
    
    public void setStaffDetails(String staffDetails) {
        this.staffDetails = staffDetails;
    }
    
    public String getStaffRole() {
        return staffRole;
    }
    
    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }
    
    public BigDecimal getStaffSalary() {
        return staffSalary;
    }
    
    public void setStaffSalary(BigDecimal staffSalary) {
        this.staffSalary = staffSalary;
    }
}




