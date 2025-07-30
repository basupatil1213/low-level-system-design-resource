package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Client class that uses the Abstract Factory to create and assemble vehicle parts.
 * This class demonstrates how the client code remains independent of concrete
 * product classes and only depends on abstract interfaces.
 */
public class VehicleAssembler {
    private final Engine engine;
    private final Tire tire;
    private final Brake brake;
    private final String vehicleType;

    /**
     * Constructor that takes a factory and creates all necessary parts.
     * This ensures that all parts belong to the same product family.
     * 
     * @param factory The factory to use for creating vehicle parts
     */
    public VehicleAssembler(VehicleFactory factory) {
        this.engine = factory.createEngine();
        this.tire = factory.createTire();
        this.brake = factory.createBrake();
        this.vehicleType = factory.getVehicleType();
    }

    /**
     * Assembles and demonstrates the functionality of all vehicle parts.
     */
    public void assemble() {
        System.out.println("=== Assembling " + vehicleType + " ===");
        
        // Start the engine
        engine.start();
        System.out.println("Engine specs: " + engine.getHorsepower() + "HP, " + engine.getFuelType());
        
        // Test the tires
        tire.rotate();
        System.out.println("Tire specs: " + tire.getDiameter() + "\" diameter, " + 
                         tire.getTreadPattern() + " tread, " + tire.getMaxPressure() + " PSI max");
        
        // Test the brakes
        brake.apply();
        System.out.println("Brake specs: " + brake.getBrakeType() + " type, " + 
                         brake.getMaxForce() + "N max force, ABS: " + brake.isAntiLockEnabled());
        
        System.out.println(vehicleType + " assembly completed successfully!\n");
    }
    
    /**
     * Demonstrates driving scenario with all components working together.
     */
    public void demonstrateDriving() {
        System.out.println("=== " + vehicleType + " Driving Demonstration ===");
        
        // Start journey
        engine.start();
        tire.rotate();
        
        // Emergency braking scenario
        System.out.println("Emergency braking scenario:");
        brake.apply();
        tire.brake();
        brake.release();
        
        // End journey
        engine.stop();
        
        System.out.println(vehicleType + " driving demonstration completed!\n");
    }
    
    /**
     * Performs maintenance on all vehicle parts.
     */
    public void performMaintenance() {
        System.out.println("=== " + vehicleType + " Maintenance ===");
        engine.performMaintenance();
        System.out.println(vehicleType + " maintenance completed!\n");
    }
}
