package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Concrete product implementation for Truck Engine.
 * Part of the Truck family of products.
 */
public class TruckEngine implements Engine {
    private static final int HORSEPOWER = 400;
    private static final String FUEL_TYPE = "Diesel";
    
    @Override
    public void start() {
        System.out.println("Truck Engine started with V8 diesel configuration");
    }
    
    @Override
    public void stop() {
        System.out.println("Truck Engine stopped with engine brake assistance");
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
        System.out.println("Performing truck engine maintenance: Oil change, diesel filter replacement, turbo inspection");
    }
}
