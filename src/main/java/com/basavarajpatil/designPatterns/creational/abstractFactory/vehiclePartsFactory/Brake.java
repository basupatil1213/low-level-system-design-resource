package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Abstract product interface for Brake components.
 * Defines the contract that all brake implementations must follow.
 */
public interface Brake {
    void apply();
    void release();
    String getBrakeType();
    int getMaxForce();
    boolean isAntiLockEnabled();
}
