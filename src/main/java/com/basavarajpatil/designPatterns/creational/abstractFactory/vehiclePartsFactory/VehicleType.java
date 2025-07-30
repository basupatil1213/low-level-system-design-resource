package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Enum representing different vehicle types.
 * Provides type safety and helps in factory selection.
 */
public enum VehicleType {
    CAR("Car", "Four-wheeled passenger vehicle"),
    BIKE("Bike", "Two-wheeled motor vehicle"),
    TRUCK("Truck", "Heavy-duty cargo vehicle");
    
    private final String displayName;
    private final String description;
    
    VehicleType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
}
