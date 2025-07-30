package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Abstract product interface for Engine components.
 * Defines the contract that all engine implementations must follow.
 */
public interface Engine {
    void start();
    void stop();
    int getHorsepower();
    String getFuelType();
    void performMaintenance();
}
