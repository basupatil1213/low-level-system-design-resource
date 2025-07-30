package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Car Tire.
 * Part of the Car family of products.
 */
public class CarTire implements Tire {
    private static final int DIAMETER = 17;
    private static final String TREAD_PATTERN = "All-Season";
    private static final int MAX_PRESSURE = 35;
    
    @Override
    public void rotate() {
        System.out.println("Car tire rotating smoothly on asphalt with all-season tread");
    }
    
    @Override
    public void brake() {
        System.out.println("Car tire providing excellent braking grip");
    }
    
    @Override
    public int getDiameter() {
        return DIAMETER;
    }
    
    @Override
    public String getTreadPattern() {
        return TREAD_PATTERN;
    }
    
    @Override
    public int getMaxPressure() {
        return MAX_PRESSURE;
    }
}
