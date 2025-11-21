package com.temple.temple_database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "poojas")
public class Pooja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Pooja name is required")
    @Column(nullable = false, unique = true)
    private String poojaName;
    
    @Column(columnDefinition = "TEXT")
    private String poojaDetails;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private BigDecimal poojaPrice;
    
    @OneToMany(mappedBy = "pooja", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"pooja"})
    private List<Member> members;
    
    public Pooja() {
    }
    
    public Pooja(String poojaName, String poojaDetails, BigDecimal poojaPrice) {
        this.poojaName = poojaName;
        this.poojaDetails = poojaDetails;
        this.poojaPrice = poojaPrice;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPoojaName() {
        return poojaName;
    }
    
    public void setPoojaName(String poojaName) {
        this.poojaName = poojaName;
    }
    
    public String getPoojaDetails() {
        return poojaDetails;
    }
    
    public void setPoojaDetails(String poojaDetails) {
        this.poojaDetails = poojaDetails;
    }
    
    public BigDecimal getPoojaPrice() {
        return poojaPrice;
    }
    
    public void setPoojaPrice(BigDecimal poojaPrice) {
        this.poojaPrice = poojaPrice;
    }
    
    public List<Member> getMembers() {
        return members;
    }
    
    public void setMembers(List<Member> members) {
        this.members = members;
    }
}


