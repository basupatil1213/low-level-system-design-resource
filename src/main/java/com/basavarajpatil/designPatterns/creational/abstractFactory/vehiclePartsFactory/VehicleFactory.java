package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Abstract Factory interface for creating families of related vehicle parts.
 * This is the core of the Abstract Factory pattern - it defines methods
 * for creating each type of product in the family.
 */
public interface VehicleFactory {
    Engine createEngine();
    Tire createTire();
    Brake createBrake();
    
    /**
     * Optional method to get the vehicle type this factory produces parts for
     */
    default String getVehicleType() {
        return "Unknown";
    }
}
