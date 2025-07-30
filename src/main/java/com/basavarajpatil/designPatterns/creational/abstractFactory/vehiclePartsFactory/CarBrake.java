package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Car Brake.
 * Part of the Car family of products.
 */
public class CarBrake implements Brake {
    private static final String BRAKE_TYPE = "Disc";
    private static final int MAX_FORCE = 1200;
    private static final boolean ANTI_LOCK_ENABLED = true;
    
    @Override
    public void apply() {
        System.out.println("Car disc brakes applied with ABS assistance");
    }
    
    @Override
    public void release() {
        System.out.println("Car brakes released smoothly");
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
