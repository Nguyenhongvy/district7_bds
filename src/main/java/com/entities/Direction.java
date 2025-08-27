package com.entities;

public enum Direction {
	EAST("Đông"),
    WEST("Tây"),
    SOUTH("Nam"),
    NORTH("Bắc"),
    SOUTHEAST("Đông Nam"),
    SOUTHWEST("Tây Nam"),
    NORTHEAST("Đông Bắc"),
    NORTHWEST("Tây Bắc");
    
    private final String displayName;
    
    Direction(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
