package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class CarTire implements Tire{
    @Override
    public void rotate() {
        System.out.println("Car Tire Rotating smoothly");
    }
}
