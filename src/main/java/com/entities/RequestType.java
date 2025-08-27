package com.entities;

public enum RequestType {
    VIEWING("Xem nhà"),
    CONSULTATION("Tư vấn"),
    PRICE_INQUIRY("Hỏi giá"),
    OTHER("Khác");
    
    private final String displayName;
    
    RequestType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
