package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public interface VehicleFactory {
    Engine createEngine();
    Tire createTire();
}
