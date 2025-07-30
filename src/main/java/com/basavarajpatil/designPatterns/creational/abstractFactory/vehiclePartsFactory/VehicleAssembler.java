package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

public class VehicleAssembler {
    private Engine engine;
    private Tire tire;

    public VehicleAssembler(VehicleFactory factory) {
        this.engine = factory.createEngine();
        this.tire = factory.createTire();
    }

    public void assemble() {
        engine.start();
        tire.rotate();
    }
}
