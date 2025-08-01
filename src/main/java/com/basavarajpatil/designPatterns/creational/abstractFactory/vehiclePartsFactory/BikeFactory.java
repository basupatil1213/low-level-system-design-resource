package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete Factory for creating Bike family of products.
 * This factory ensures that all created products are compatible with each other
 * and belong to the same product family (Bike).
 */
public class BikeFactory implements VehicleFactory {
    
    @Override
    public Engine createEngine() {
        return new BikeEngine();
    }

    @Override
    public Tire createTire() {
        return new BikeTire();
    }
    
    @Override
    public Brake createBrake() {
        return new BikeBrake();
    }
    
    @Override
    public String getVehicleType() {
        return "Bike";
    }
}
