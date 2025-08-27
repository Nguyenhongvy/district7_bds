package com.entities;

public enum ProjectType {
    APARTMENT("Căn hộ"),
    VILLA("Biệt thự"),
    TOWNHOUSE("Nhà phố"),
    OFFICE("Văn phòng"),
    COMMERCIAL("Thương mại");
    
    private final String displayName;
    
    ProjectType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}

