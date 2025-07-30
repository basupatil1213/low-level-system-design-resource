package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Bike Tire.
 * Part of the Bike family of products.
 */
public class BikeTire implements Tire {
    private static final int DIAMETER = 19;
    private static final String TREAD_PATTERN = "Sport";
    private static final int MAX_PRESSURE = 40;
    
    @Override
    public void rotate() {
        System.out.println("Bike tire rotating with sport tread pattern for optimal grip");
    }
    
    @Override
    public void brake() {
        System.out.println("Bike tire providing responsive braking");
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
