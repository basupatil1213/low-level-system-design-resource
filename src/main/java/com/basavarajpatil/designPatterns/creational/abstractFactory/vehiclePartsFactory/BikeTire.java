package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class BikeTire implements Tire{
    @Override
    public void rotate() {
        System.out.println("Rotating Bike Tire Smoothly");
    }
}
