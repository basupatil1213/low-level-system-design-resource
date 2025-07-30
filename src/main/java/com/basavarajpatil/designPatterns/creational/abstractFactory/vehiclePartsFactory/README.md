# 🚗 Vehicle Parts Factory - Abstract Factory Pattern

A comprehensive demonstration of the Abstract Factory pattern that creates families of related vehicle components. This system showcases how to build extensible, maintainable code for manufacturing vehicle parts across different vehicle types while ensuring compatibility within each family.

## 🎯 Problem Statement

In automotive manufacturing, different vehicle types (Cars, Bikes, Trucks) require specific families of compatible parts. The challenges include:

- **Parts Compatibility**: Ensuring parts from the same family work together
- **Type Safety**: Preventing mixing of incompatible parts (e.g., car engine with bike tires)
- **Extensibility**: Adding new vehicle types without modifying existing code
- **Consistency**: Maintaining consistent interfaces across different part families
- **Manufacturing Flexibility**: Supporting different manufacturing processes for each vehicle type

## 💡 Solution Architecture

The Abstract Factory pattern solves these challenges by:
- **Abstract Factory**: Defines the interface for creating families of related objects
- **Concrete Factories**: Implement the creation logic for specific product families
- **Abstract Products**: Define interfaces for different types of parts
- **Concrete Products**: Implement specific parts for each vehicle family
- **Client**: Uses only abstract interfaces, remaining independent of concrete classes

## 🏗️ System Architecture

```
Client → VehicleFactoryProvider → VehicleFactory (Abstract)
                                       ↓
                    ┌─────────────────────────────────────┐
                    │                                     │
              CarFactory        BikeFactory        TruckFactory
                    │                │                    │
         Creates:   │     Creates:   │         Creates:   │
    ┌──────────────┼──────────┐     │     ┌──────────────┼──────────┐
    │              │          │     │     │              │          │
CarEngine     CarTire     CarBrake  │  TruckEngine  TruckTire  TruckBrake
    │              │          │     │     │              │          │
    └──────────────┼──────────┘     │     └──────────────┼──────────┘
                   │                │                    │
            BikeEngine         BikeTire            BikeBrake
                   │                │                    │
                   └────────────────┼────────────────────┘
                                    │
                     All implement respective interfaces:
                          Engine, Tire, Brake
```

## 📁 Implementation Components

### 1. Abstract Product Interfaces

#### Engine Interface
```java
public interface Engine {
    void start();
    void stop();
    int getHorsepower();
    String getFuelType();
    void performMaintenance();
}
```
**Purpose**: Defines the contract for all engine implementations across vehicle families.

#### Tire Interface
```java
public interface Tire {
    void rotate();
    void brake();
    int getDiameter();
    String getTreadPattern();
    int getMaxPressure();
}
```
**Purpose**: Defines the contract for all tire implementations with vehicle-specific characteristics.

#### Brake Interface
```java
public interface Brake {
    void apply();
    void release();
    String getBrakeType();
    int getMaxForce();
    boolean isAntiLockEnabled();
}
```
**Purpose**: Defines the contract for all brake implementations with safety features.

### 2. Abstract Factory Interface

```java
public interface VehicleFactory {
    Engine createEngine();
    Tire createTire();
    Brake createBrake();
    default String getVehicleType() { return "Unknown"; }
}
```
**Responsibilities**:
- Declares creation methods for each product type
- Ensures all products belong to the same family
- Provides optional vehicle type identification

### 3. Concrete Product Families

#### Car Family Products
- **CarEngine**: 200HP V6 Gasoline engine with advanced maintenance features
- **CarTire**: 17" All-Season tires optimized for passenger comfort
- **CarBrake**: Disc brakes with ABS for maximum safety

#### Bike Family Products
- **BikeEngine**: 50HP Single-cylinder engine optimized for efficiency
- **BikeTire**: 19" Sport tires for optimal grip and maneuverability
- **BikeBrake**: Manual disc brakes without ABS

#### Truck Family Products
- **TruckEngine**: 400HP V8 Diesel engine for heavy-duty performance
- **TruckTire**: 22" Heavy-duty tires for maximum load capacity
- **TruckBrake**: Air brakes with maximum stopping power for safety

### 4. Concrete Factories

#### CarFactory
```java
public class CarFactory implements VehicleFactory {
    @Override
    public Engine createEngine() { return new CarEngine(); }
    @Override
    public Tire createTire() { return new CarTire(); }
    @Override
    public Brake createBrake() { return new CarBrake(); }
}
```

#### BikeFactory & TruckFactory
Similar implementations ensuring family consistency.

### 5. Factory Provider & Type Safety

#### VehicleType Enum
```java
public enum VehicleType {
    CAR("Car", "Four-wheeled passenger vehicle"),
    BIKE("Bike", "Two-wheeled motor vehicle"),
    TRUCK("Truck", "Heavy-duty cargo vehicle");
}
```
**Benefits**: Compile-time type safety and self-documenting code.

#### VehicleFactoryProvider
```java
public class VehicleFactoryProvider {
    public static VehicleFactory getFactory(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> new CarFactory();
            case BIKE -> new BikeFactory();
            case TRUCK -> new TruckFactory();
        };
    }
}
```
**Benefits**: Centralized factory creation with type safety.

### 6. Client & Assembly

#### VehicleAssembler
```java
public class VehicleAssembler {
    private final Engine engine;
    private final Tire tire;
    private final Brake brake;
    
    public VehicleAssembler(VehicleFactory factory) {
        this.engine = factory.createEngine();
        this.tire = factory.createTire();
        this.brake = factory.createBrake();
    }
}
```
**Key Features**:
- Uses only abstract interfaces
- Guarantees part compatibility
- Supports assembly, driving, and maintenance scenarios

## 🔧 Key Design Principles Demonstrated

### 1. Abstract Factory Pattern Core Benefits
- **Family Consistency**: All parts from a factory belong to the same family
- **Type Safety**: Compile-time prevention of incompatible part mixing
- **Extensibility**: New vehicle types add without changing existing code
- **Encapsulation**: Creation logic encapsulated in concrete factories

### 2. SOLID Principles
- **Single Responsibility**: Each class has one reason to change
- **Open/Closed**: Open for extension (new vehicle types), closed for modification
- **Liskov Substitution**: All concrete products can replace their abstractions
- **Interface Segregation**: Focused interfaces for each product type
- **Dependency Inversion**: Client depends on abstractions, not concretions

### 3. Additional Patterns
- **Factory Method**: VehicleFactoryProvider uses Factory Method to create factories
- **Strategy**: Different behavior strategies for each vehicle family
- **Template Method**: Common assembly process with family-specific implementations

## 🧪 Usage Examples

### Basic Vehicle Assembly
```java
// Type-safe factory creation
VehicleFactory carFactory = VehicleFactoryProvider.getFactory(VehicleType.CAR);
VehicleAssembler carAssembler = new VehicleAssembler(carFactory);

// Assemble and test
carAssembler.assemble();
carAssembler.demonstrateDriving();
carAssembler.performMaintenance();
```

### Dynamic Factory Selection
```java
// Configuration-driven creation
String vehicleType = "truck"; // From config file
VehicleFactory factory = VehicleFactoryProvider.getFactory(vehicleType);
VehicleAssembler assembler = new VehicleAssembler(factory);
```

### Batch Manufacturing
```java
public class VehicleManufacturingLine {
    public void produceVehicles(List<VehicleType> orders) {
        for (VehicleType type : orders) {
            VehicleFactory factory = VehicleFactoryProvider.getFactory(type);
            VehicleAssembler assembler = new VehicleAssembler(factory);
            
            System.out.println("Manufacturing: " + type.getDisplayName());
            assembler.assemble();
            
            // Quality testing
            assembler.demonstrateDriving();
        }
    }
}
```

### Parts Compatibility Validation
```java
public class QualityControl {
    public boolean validatePartCompatibility(VehicleFactory factory) {
        Engine engine = factory.createEngine();
        Tire tire = factory.createTire();
        Brake brake = factory.createBrake();
        
        // All parts from same factory are guaranteed compatible
        return engine.getFuelType().equals("Gasoline") || 
               engine.getFuelType().equals("Diesel");
    }
}
```

## 🔄 Extending the System

### Adding Electric Vehicle Support
```java
// 1. Add new vehicle type
public enum VehicleType {
    CAR, BIKE, TRUCK, ELECTRIC_CAR
}

// 2. Create electric-specific products
public class ElectricEngine implements Engine {
    @Override
    public String getFuelType() { return "Electric"; }
    @Override
    public int getHorsepower() { return 300; }
    // ... other methods
}

public class ElectricTire implements Tire {
    @Override
    public String getTreadPattern() { return "Low-Rolling-Resistance"; }
    // ... other methods
}

public class RegenerativeBrake implements Brake {
    @Override
    public String getBrakeType() { return "Regenerative"; }
    // ... other methods
}

// 3. Create electric vehicle factory
public class ElectricCarFactory implements VehicleFactory {
    @Override
    public Engine createEngine() { return new ElectricEngine(); }
    @Override
    public Tire createTire() { return new ElectricTire(); }
    @Override
    public Brake createBrake() { return new RegenerativeBrake(); }
}

// 4. Update factory provider
case ELECTRIC_CAR -> new ElectricCarFactory();
```

### Adding Hybrid Vehicle Family
```java
public class HybridEngine implements Engine {
    private final Engine gasEngine;
    private final Engine electricMotor;
    
    public HybridEngine() {
        this.gasEngine = new CarEngine();
        this.electricMotor = new ElectricEngine();
    }
    
    @Override
    public void start() {
        electricMotor.start();
        System.out.println("Starting in electric mode");
    }
    
    public void switchToGasoline() {
        gasEngine.start();
        System.out.println("Switching to gasoline mode");
    }
}
```

### Adding Advanced Features
```java
// Smart tire with sensors
public class SmartTire implements Tire {
    private int currentPressure;
    private boolean pressureMonitoringEnabled = true;
    
    @Override
    public void rotate() {
        monitorPressure();
        super.rotate();
    }
    
    private void monitorPressure() {
        if (pressureMonitoringEnabled && currentPressure < getMaxPressure() * 0.8) {
            System.out.println("Warning: Low tire pressure detected!");
        }
    }
}

// Advanced brake system
public class AdaptiveBrake implements Brake {
    private boolean emergencyBrakingEnabled = true;
    
    @Override
    public void apply() {
        if (emergencyBrakingEnabled) {
            System.out.println("Emergency braking assistance activated");
        }
        super.apply();
    }
}
```

## ✅ Pattern Validation

### Abstract Factory Pattern Checklist
- ✅ **Abstract Factory Interface**: VehicleFactory defines product creation methods
- ✅ **Concrete Factories**: CarFactory, BikeFactory, TruckFactory implement creation
- ✅ **Abstract Products**: Engine, Tire, Brake interfaces define product contracts
- ✅ **Concrete Products**: Family-specific implementations (CarEngine, BikeEngine, etc.)
- ✅ **Client Independence**: VehicleAssembler depends only on abstractions
- ✅ **Family Consistency**: All products from one factory belong to the same family

### Design Quality Metrics
- ✅ **Type Safety**: Enum-based vehicle types prevent runtime errors
- ✅ **Extensibility**: New vehicle types add without changing existing code
- ✅ **Maintainability**: Clear separation of concerns and single responsibility
- ✅ **Testability**: Each component can be unit tested independently
- ✅ **Documentation**: Comprehensive JavaDoc and architectural documentation

## ❌ Current Limitations

1. **No Persistence**: Parts don't maintain state between operations
2. **Static Configuration**: Factory selection is compile-time only
3. **Limited Error Recovery**: Basic exception handling
4. **No Performance Optimization**: No caching or pooling
5. **Missing Real-world Features**: No part serialization or networking

## 🚀 Production Enhancements

### Configuration-Driven Factory Selection
```java
public class ConfigurableFactoryProvider {
    private static final Properties config = loadConfiguration();
    
    public static VehicleFactory getFactory(String configKey) {
        String factoryClass = config.getProperty(configKey);
        return (VehicleFactory) Class.forName(factoryClass).getConstructor().newInstance();
    }
}
```

### Performance Optimizations
```java
public class CachedVehicleFactory implements VehicleFactory {
    private final Map<Class<?>, Object> partCache = new ConcurrentHashMap<>();
    private final VehicleFactory delegate;
    
    @Override
    public Engine createEngine() {
        return (Engine) partCache.computeIfAbsent(Engine.class, 
            k -> delegate.createEngine());
    }
}
```

### Async Manufacturing
```java
public class AsyncVehicleAssembler {
    public CompletableFuture<Void> assembleAsync(VehicleFactory factory) {
        return CompletableFuture.supplyAsync(() -> factory.createEngine())
            .thenCombine(
                CompletableFuture.supplyAsync(() -> factory.createTire()),
                CompletableFuture.supplyAsync(() -> factory.createBrake()),
                this::assembleComponents);
    }
}
```

### Dependency Injection Integration
```java
@Component
public class SpringVehicleAssembler {
    @Autowired
    @Qualifier("carFactory")
    private VehicleFactory carFactory;
    
    @Autowired
    @Qualifier("bikeFactory")
    private VehicleFactory bikeFactory;
}
```

## 🎓 Interview Questions & Answers

### Pattern Understanding

**Q**: What's the key difference between Factory Method and Abstract Factory patterns?  
**A**: Factory Method creates one type of object, while Abstract Factory creates families of related objects. Factory Method uses inheritance, Abstract Factory uses composition.

**Q**: How does Abstract Factory ensure product family consistency?  
**A**: Each concrete factory creates products that belong to the same family. The pattern prevents mixing products from different families at compile time.

**Q**: Why use VehicleFactoryProvider instead of direct factory instantiation?  
**A**: It provides centralized factory creation, type safety through enums, and easier configuration management. It also demonstrates the Factory Method pattern working with Abstract Factory.

### Design Questions

**Q**: How would you handle cross-cutting concerns like logging or monitoring?  
**A**: Use the Decorator pattern to wrap concrete products, or implement aspect-oriented programming with annotations for cleaner separation.

**Q**: How would you support runtime factory configuration?  
**A**: Use reflection-based factory loading, dependency injection containers, or configuration files to dynamically select factory implementations.

**Q**: How would you handle versioning of product families?  
**A**: Create versioned factory interfaces (VehicleFactoryV1, VehicleFactoryV2) or use strategy pattern with version-specific implementations.

### Scalability Questions

**Q**: How would you scale this for 50+ vehicle types?  
**A**: Use registration-based factory discovery, annotation-driven factory configuration, or plugin architecture with dynamic class loading.

**Q**: How would you handle part customization within families?  
**A**: Use Builder pattern for complex part creation, Strategy pattern for behavior variation, or Template Method for customizable algorithms.

**Q**: How would you support multi-tenant manufacturing?  
**A**: Add tenant context to factory provider, implement tenant-specific factories, or use context-aware product creation.

## 🔗 Real-World Applications

### Automotive Industry
- **BMW Manufacturing**: Different product lines (X Series, 3 Series) with compatible parts
- **Tesla**: Electric vehicle families with specific battery, motor, and control systems
- **Ford**: Truck, car, and SUV lines with family-specific components

### Software Development
- **UI Frameworks**: Cross-platform UI components (Windows, macOS, Linux themes)
- **Database Drivers**: JDBC drivers for different database families
- **Gaming Engines**: Renderer factories for different graphics APIs (DirectX, OpenGL, Vulkan)

### Technology Platforms
- **Cloud Providers**: AWS, Azure, GCP with family-specific services
- **Mobile Development**: iOS and Android platform-specific components
- **Enterprise Software**: ERP systems with industry-specific modules

## 📊 Performance Characteristics

| Factory Type | Creation Time | Memory Usage | Thread Safety | Extensibility |
|--------------|---------------|--------------|---------------|---------------|
| CarFactory   | O(1)          | Low          | ✅ Stateless | ✅ High       |
| BikeFactory  | O(1)          | Low          | ✅ Stateless | ✅ High       |
| TruckFactory | O(1)          | Low          | ✅ Stateless | ✅ High       |

| Product Type | Initialization | Behavior Calls | Memory Footprint |
|--------------|----------------|----------------|------------------|
| Engine       | Fast           | O(1)           | Small            |
| Tire         | Fast           | O(1)           | Small            |
| Brake        | Fast           | O(1)           | Small            |

## 🔧 Running the Example

```bash
# Compile the vehicle parts factory system
cd /path/to/project
javac -d target/classes src/main/java/com/basavarajpatil/designPatterns/creational/abstractFactory/vehiclePartsFactory/*.java

# Run the comprehensive demonstration
java -cp target/classes com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory.Client
```

**Expected Output Highlights:**
- ✅ Assembly demonstration for all vehicle types
- ✅ Driving scenarios with emergency braking
- ✅ Maintenance procedures for each vehicle family
- ✅ Dynamic factory selection with type safety
- ✅ Error handling for unsupported vehicle types
- ✅ Parts specification display (HP, pressure, force ratings)

## 🔄 Pattern Relationships

```
Abstract Factory Pattern Relationships:
┌─────────────────┐    uses     ┌──────────────────┐
│ Factory Method  │ ──────────→ │ Abstract Factory │
│ (Provider)      │             │ (Main Pattern)   │
└─────────────────┘             └──────────────────┘
                                          │ creates
                                          ▼
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│ Strategy        │    │ Product Families │    │ Template Method │
│ (Behavior)      │◄──►│ (Car, Bike,     │◄──►│ (Assembly)      │
└─────────────────┘    │  Truck)          │    └─────────────────┘
                       └──────────────────┘
```

---

💡 **Key Takeaway**: The Abstract Factory pattern provides a robust solution for creating families of related objects while maintaining type safety, extensibility, and family consistency. This vehicle parts factory demonstrates how the pattern scales from simple demonstrations to production-ready manufacturing systems.
