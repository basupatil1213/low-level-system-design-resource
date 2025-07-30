package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class CarEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Car Engine started");
    }
}
