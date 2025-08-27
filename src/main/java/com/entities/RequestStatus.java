package com.entities;


public enum RequestStatus {
    NEW("Mới"),
    CONTACTED("Đã liên hệ"),
    COMPLETED("Hoàn thành"),
    CANCELLED("Đã hủy");
    
    private final String displayName;
    
    RequestStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}