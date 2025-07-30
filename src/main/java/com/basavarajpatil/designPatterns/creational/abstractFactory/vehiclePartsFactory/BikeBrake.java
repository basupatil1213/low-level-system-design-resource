package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Bike Brake.
 * Part of the Bike family of products.
 */
public class BikeBrake implements Brake {
    private static final String BRAKE_TYPE = "Disc";
    private static final int MAX_FORCE = 400;
    private static final boolean ANTI_LOCK_ENABLED = false;
    
    @Override
    public void apply() {
        System.out.println("Bike disc brakes applied with manual control");
    }
    
    @Override
    public void release() {
        System.out.println("Bike brakes released");
    }
    
    @Override
    public String getBrakeType() {
        return BRAKE_TYPE;
    }
    
    @Override
    public int getMaxForce() {
        return MAX_FORCE;
    }
    
    @Override
    public boolean isAntiLockEnabled() {
        return ANTI_LOCK_ENABLED;
    }
}
