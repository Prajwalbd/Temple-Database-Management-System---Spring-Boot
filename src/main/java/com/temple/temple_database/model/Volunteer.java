package com.temple.temple_database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "volunteers")
public class Volunteer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Volunteer name is required")
    @Column(nullable = false)
    private String volunteerName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Column(nullable = false)
    private String volunteerPhone;
    
    @Column(columnDefinition = "TEXT")
    private String volunteerDetail;
    
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;
    
    public Volunteer() {
    }
    
    public Volunteer(String volunteerName, String volunteerPhone, String volunteerDetail, LocalDate date) {
        this.volunteerName = volunteerName;
        this.volunteerPhone = volunteerPhone;
        this.volunteerDetail = volunteerDetail;
        this.date = date;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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





