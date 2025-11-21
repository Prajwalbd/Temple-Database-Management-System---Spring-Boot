package com.temple.temple_database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "members")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Member name is required")
    @Column(nullable = false)
    private String memberName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    @Column(nullable = false)
    private String memberPhoneNo;
    
    @Column(columnDefinition = "TEXT")
    private String memberDetails;
    
    @NotNull(message = "Date is required")
    @Column(nullable = false)
    private LocalDate date;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pooja_id", nullable = false)
    @NotNull(message = "Pooja is required")
    @JsonIgnoreProperties({"members"})
    private Pooja pooja;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @PrePersist
    @PreUpdate
    private void setPriceFromPooja() {
        if (pooja != null && pooja.getPoojaPrice() != null) {
            this.price = pooja.getPoojaPrice();
        }
    }
    
    public Member() {
    }
    
    public Member(String memberName, String memberPhoneNo, String memberDetails, LocalDate date, Pooja pooja) {
        this.memberName = memberName;
        this.memberPhoneNo = memberPhoneNo;
        this.memberDetails = memberDetails;
        this.date = date;
        this.pooja = pooja;
        setPriceFromPooja();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMemberName() {
        return memberName;
    }
    
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    public String getMemberPhoneNo() {
        return memberPhoneNo;
    }
    
    public void setMemberPhoneNo(String memberPhoneNo) {
        this.memberPhoneNo = memberPhoneNo;
    }
    
    public String getMemberDetails() {
        return memberDetails;
    }
    
    public void setMemberDetails(String memberDetails) {
        this.memberDetails = memberDetails;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public Pooja getPooja() {
        return pooja;
    }
    
    public void setPooja(Pooja pooja) {
        this.pooja = pooja;
        setPriceFromPooja();
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}


