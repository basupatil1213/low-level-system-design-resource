package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Client application demonstrating the Abstract Factory pattern.
 * This class shows how to use the pattern to create families of related objects
 * without specifying their concrete classes.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("🚗 Vehicle Parts Factory - Abstract Factory Pattern Demo");
        System.out.println("=".repeat(60));
        
        // Demonstrate factory creation and assembly for all vehicle types
        demonstrateBasicAssembly();
        
        // Demonstrate driving scenarios
        demonstrateDrivingScenarios();
        
        // Demonstrate maintenance
        demonstrateMaintenance();
        
        // Demonstrate dynamic factory selection
        demonstrateDynamicFactorySelection();
        
        // Demonstrate error handling
        demonstrateErrorHandling();
    }
    
    /**
     * Demonstrates basic assembly for all vehicle types
     */
    private static void demonstrateBasicAssembly() {
        System.out.println("\n1️⃣ BASIC ASSEMBLY DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        for (VehicleType vehicleType : VehicleType.values()) {
            VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleType);
            VehicleAssembler assembler = new VehicleAssembler(factory);
            assembler.assemble();
        }
    }
    
    /**
     * Demonstrates driving scenarios for all vehicle types
     */
    private static void demonstrateDrivingScenarios() {
        System.out.println("\n2️⃣ DRIVING SCENARIOS DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        for (VehicleType vehicleType : VehicleType.values()) {
            VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleType);
            VehicleAssembler assembler = new VehicleAssembler(factory);
            assembler.demonstrateDriving();
        }
    }
    
    /**
     * Demonstrates maintenance for all vehicle types
     */
    private static void demonstrateMaintenance() {
        System.out.println("\n3️⃣ MAINTENANCE DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        for (VehicleType vehicleType : VehicleType.values()) {
            VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleType);
            VehicleAssembler assembler = new VehicleAssembler(factory);
            assembler.performMaintenance();
        }
    }
    
    /**
     * Demonstrates dynamic factory selection using string input
     */
    private static void demonstrateDynamicFactorySelection() {
        System.out.println("\n4️⃣ DYNAMIC FACTORY SELECTION");
        System.out.println("-".repeat(40));
        
        String[] vehicleNames = {"car", "BIKE", "Truck"};
        
        for (String vehicleName : vehicleNames) {
            try {
                System.out.println("Creating factory for: " + vehicleName);
                VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleName);
                System.out.println("✅ Successfully created " + factory.getVehicleType() + " factory");
                
                // Quick demonstration
                Engine engine = factory.createEngine();
                System.out.println("Sample engine: " + engine.getHorsepower() + "HP " + engine.getFuelType());
                System.out.println();
                
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
                System.out.println();
            }
        }
    }
    
    /**
     * Demonstrates error handling for unsupported vehicle types
     */
    private static void demonstrateErrorHandling() {
        System.out.println("\n5️⃣ ERROR HANDLING DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        String[] invalidTypes = {"airplane", "boat", "spaceship"};
        
        for (String invalidType : invalidTypes) {
            try {
                System.out.println("Attempting to create factory for: " + invalidType);
                VehicleFactory factory = VehicleFactoryProvider.getFactory(invalidType);
                System.out.println("✅ Factory created: " + factory.getVehicleType() + " (this shouldn't happen!)");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Expected error: " + e.getMessage());
            }
            System.out.println();
        }
        
        // Show supported types
        System.out.println("🔧 Supported vehicle types:");
        for (VehicleType type : VehicleFactoryProvider.getSupportedTypes()) {
            System.out.println("  • " + type.getDisplayName() + ": " + type.getDescription());
        }
    }
}
