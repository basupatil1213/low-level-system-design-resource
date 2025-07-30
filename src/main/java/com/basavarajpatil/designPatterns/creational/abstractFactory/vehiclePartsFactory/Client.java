package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class Client {
    public static void main(String[] args) {
        System.out.println("\n----- Assembling Car -----\n");
        VehicleAssembler carAssemble = new VehicleAssembler(new CarFactory());
        carAssemble.assemble();

        System.out.println("\n----- Assembling Bike -----\n");
        VehicleAssembler bikeAssembler = new VehicleAssembler(new BikeFactory());
        bikeAssembler.assemble();
    }
}
