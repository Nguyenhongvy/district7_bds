package com.entities;

public enum UnitStatus {
	AVAILABLE("Có sẵn"), 
	RESERVED("Đặt chỗ"), 
	SOLD("Đã bán"),
	RENTED("Đã cho thuê");

	private final String displayName;

	UnitStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
