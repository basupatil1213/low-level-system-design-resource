package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Truck Tire.
 * Part of the Truck family of products.
 */
public class TruckTire implements Tire {
    private static final int DIAMETER = 22;
    private static final String TREAD_PATTERN = "Heavy-Duty";
    private static final int MAX_PRESSURE = 80;
    
    @Override
    public void rotate() {
        System.out.println("Truck tire rotating with heavy-duty tread for maximum load capacity");
    }
    
    @Override
    public void brake() {
        System.out.println("Truck tire providing powerful braking for heavy loads");
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
