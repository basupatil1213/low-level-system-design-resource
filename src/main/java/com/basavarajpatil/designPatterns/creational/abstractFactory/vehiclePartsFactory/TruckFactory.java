package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete Factory for creating Truck family of products.
 * This factory ensures that all created products are compatible with each other
 * and belong to the same product family (Truck).
 */
public class TruckFactory implements VehicleFactory {
    
    @Override
    public Engine createEngine() {
        return new TruckEngine();
    }

    @Override
    public Tire createTire() {
        return new TruckTire();
    }
    
    @Override
    public Brake createBrake() {
        return new TruckBrake();
    }
    
    @Override
    public String getVehicleType() {
        return "Truck";
    }
}
