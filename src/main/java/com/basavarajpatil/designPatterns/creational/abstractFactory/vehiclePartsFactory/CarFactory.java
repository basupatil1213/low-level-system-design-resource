package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete Factory for creating Car family of products.
 * This factory ensures that all created products are compatible with each other
 * and belong to the same product family (Car).
 */
public class CarFactory implements VehicleFactory {
    
    @Override
    public Engine createEngine() {
        return new CarEngine();
    }

    @Override
    public Tire createTire() {
        return new CarTire();
    }
    
    @Override
    public Brake createBrake() {
        return new CarBrake();
    }
    
    @Override
    public String getVehicleType() {
        return "Car";
    }
}
