package com.temple.temple_database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PoojaRequest {
    
    @NotBlank(message = "Pooja name is required")
    private String poojaName;
    
    private String poojaDetails;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal poojaPrice;
    
    public PoojaRequest() {
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
}




