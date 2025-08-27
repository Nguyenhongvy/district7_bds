package com.entities;

public enum ProjectStatus {
    PLANNING("Đang lên kế hoạch"),
    CONSTRUCTION("Đang thi công"),
    COMPLETED("Hoàn thành"),
    HANDOVER("Bàn giao");
    
    private final String displayName;
    
    ProjectStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
