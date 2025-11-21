package com.temple.temple_database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Hall name is required")
    @Column(nullable = false, unique = true)
    private String hallName;
    
    @Column(columnDefinition = "TEXT")
    private String hallDetails;
    
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hall"})
    private List<HallBooking> bookings;
    
    public Hall() {
    }
    
    public Hall(String hallName, String hallDetails) {
        this.hallName = hallName;
        this.hallDetails = hallDetails;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public List<HallBooking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<HallBooking> bookings) {
        this.bookings = bookings;
    }
}


