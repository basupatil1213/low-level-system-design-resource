package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Car Engine.
 * Part of the Car family of products.
 */
public class CarEngine implements Engine {
    private static final int HORSEPOWER = 200;
    private static final String FUEL_TYPE = "Gasoline";
    
    @Override
    public void start() {
        System.out.println("Car Engine started with V6 configuration");
    }
    
    @Override
    public void stop() {
        System.out.println("Car Engine stopped gracefully");
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
        System.out.println("Performing car engine maintenance: Oil change, filter replacement");
    }
}
