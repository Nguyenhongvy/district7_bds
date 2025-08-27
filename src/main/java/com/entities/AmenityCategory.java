package com.entities;

public enum AmenityCategory {
    INTERNAL("Nội khu"),
    EXTERNAL("Ngoại khu"),
    TRANSPORTATION("Giao thông"),
    EDUCATION("Giáo dục"),
    HEALTHCARE("Y tế"),
    SHOPPING("Mua sắm"),
    ENTERTAINMENT("Giải trí");
    
    private final String displayName;
    
    AmenityCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
