package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Abstract product interface for Tire components.
 * Defines the contract that all tire implementations must follow.
 */
public interface Tire {
    void rotate();
    void brake();
    int getDiameter();
    String getTreadPattern();
    int getMaxPressure();
}
