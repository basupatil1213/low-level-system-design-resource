package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class BikeEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Bike Engine Started");
    }
}
