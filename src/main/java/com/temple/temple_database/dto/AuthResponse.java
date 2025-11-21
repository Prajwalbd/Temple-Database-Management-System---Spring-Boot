package com.temple.temple_database.dto;

public class AuthResponse {
    private String message;
    private boolean success;
    private Long userId;
    private String name;
    
    public AuthResponse() {
    }
    
    public AuthResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    
    public AuthResponse(String message, boolean success, Long userId, String name) {
        this.message = message;
        this.success = success;
        this.userId = userId;
        this.name = name;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}


