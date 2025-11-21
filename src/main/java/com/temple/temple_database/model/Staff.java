package com.temple.temple_database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "staff")
public class Staff {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Staff name is required")
    @Column(nullable = false)
    private String staffName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Column(nullable = false)
    private String staffPhone;
    
    @Column(columnDefinition = "TEXT")
    private String staffDetails;
    
    @NotBlank(message = "Staff role is required")
    @Column(nullable = false)
    private String staffRole;
    
    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be positive")
    @Column(nullable = false)
    private BigDecimal staffSalary;
    
    public Staff() {
    }
    
    public Staff(String staffName, String staffPhone, String staffDetails, String staffRole, BigDecimal staffSalary) {
        this.staffName = staffName;
        this.staffPhone = staffPhone;
        this.staffDetails = staffDetails;
        this.staffRole = staffRole;
        this.staffSalary = staffSalary;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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




