package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Bike Engine.
 * Part of the Bike family of products.
 */
public class BikeEngine implements Engine {
    private static final int HORSEPOWER = 50;
    private static final String FUEL_TYPE = "Gasoline";
    
    @Override
    public void start() {
        System.out.println("Bike Engine started with single cylinder configuration");
    }
    
    @Override
    public void stop() {
        System.out.println("Bike Engine stopped");
    }
    
    @Override
    public int getHorsepower() {
        return HORSEPOWER;
    }
    
    @Override
    public String getFuelType() {
        return FUEL_TYPE;
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("Performing bike engine maintenance: Oil change, spark plug replacement");
    }
}
