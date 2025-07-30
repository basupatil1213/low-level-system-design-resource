package com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory;

/**
 * Factory Provider that creates appropriate concrete factories based on vehicle type.
 * This demonstrates the Factory Method pattern working alongside Abstract Factory.
 */
public class VehicleFactoryProvider {
    
    /**
     * Creates and returns the appropriate vehicle factory based on the vehicle type.
     * 
     * @param vehicleType The type of vehicle for which to create a factory
     * @return A concrete factory for the specified vehicle type
     * @throws IllegalArgumentException if the vehicle type is not supported
     */
    public static VehicleFactory getFactory(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> new CarFactory();
            case BIKE -> new BikeFactory();
            case TRUCK -> new TruckFactory();
        };
    }
    
    /**
     * Creates a factory based on string input (useful for configuration-driven creation)
     * 
     * @param vehicleTypeName The name of the vehicle type
     * @return A concrete factory for the specified vehicle type
     * @throws IllegalArgumentException if the vehicle type name is not recognized
     */
    public static VehicleFactory getFactory(String vehicleTypeName) {
        try {
            VehicleType vehicleType = VehicleType.valueOf(vehicleTypeName.toUpperCase());
            return getFactory(vehicleType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleTypeName + 
                ". Supported types: CAR, BIKE, TRUCK");
        }
    }
    
    /**
     * Returns all supported vehicle types
     * 
     * @return Array of all supported vehicle types
     */
    public static VehicleType[] getSupportedTypes() {
        return VehicleType.values();
    }
}
