package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Truck Brake.
 * Part of the Truck family of products.
 */
public class TruckBrake implements Brake {
    private static final String BRAKE_TYPE = "Air";
    private static final int MAX_FORCE = 2000;
    private static final boolean ANTI_LOCK_ENABLED = true;
    
    @Override
    public void apply() {
        System.out.println("Truck air brakes applied with maximum stopping power");
    }
    
    @Override
    public void release() {
        System.out.println("Truck air brakes released with characteristic hiss sound");
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
