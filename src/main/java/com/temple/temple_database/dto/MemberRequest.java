package com.temple.temple_database.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class MemberRequest {
    
    @NotBlank(message = "Member name is required")
    private String memberName;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String memberPhoneNo;
    
    private String memberDetails;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotNull(message = "Pooja ID is required")
    private Long poojaId;
    
    public MemberRequest() {
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
    
    public Long getPoojaId() {
        return poojaId;
    }
    
    public void setPoojaId(Long poojaId) {
        this.poojaId = poojaId;
    }
}





