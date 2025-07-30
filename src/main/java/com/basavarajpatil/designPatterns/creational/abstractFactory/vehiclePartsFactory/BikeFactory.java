package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class BikeFactory implements VehicleFactory{
    @Override
    public Engine createEngine() {
        return new BikeEngine();
    }

    @Override
    public Tire createTire() {
        return new BikeTire();
    }
}
