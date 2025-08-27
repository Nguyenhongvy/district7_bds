package com.entities;

public enum ImageType {
	EXTERIOR("Ngoại thất"),
    INTERIOR("Nội thất"),
    AMENITY("Tiện ích"),
    FLOOR_PLAN("Mặt bằng"),
    LOCATION("Vị trí"),
    GALLERY("Thư viện ảnh");
    
    private final String displayName;
    
    ImageType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
