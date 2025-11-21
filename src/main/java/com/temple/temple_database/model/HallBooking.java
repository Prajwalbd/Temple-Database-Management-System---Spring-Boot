package com.temple.temple_database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hall_bookings")
public class HallBooking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Column(nullable = false)
    private String phoneNo;
    
    @Column(columnDefinition = "TEXT")
    private String details;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private BigDecimal price;
    
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id", nullable = false)
    @NotNull(message = "Hall is required")
    @JsonIgnoreProperties({"bookings"})
    private Hall hall;
    
    public HallBooking() {
    }
    
    public HallBooking(String name, String phoneNo, String details, BigDecimal price, LocalDate date, Hall hall) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.details = details;
        this.price = price;
        this.date = date;
        this.hall = hall;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getDetails() {
        return details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Hall getHall() {
        return hall;
    }
    
    public void setHall(Hall hall) {
        this.hall = hall;
    }
}

