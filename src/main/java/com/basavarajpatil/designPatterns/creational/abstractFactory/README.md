# 🏭 Abstract Factory Design Pattern

The Abstract Factory Design Pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. It's a creational pattern that encapsulates a group of individual factories that have a common theme.

## 🎯 Problem Statement

When you need to create families of related products that must be used together, and you want to ensure that the family of products is consistent. Common scenarios include:

- Cross-platform GUI components (Windows, macOS, Linux)
- Database connectors for different vendors (MySQL, PostgreSQL, Oracle)
- Document exporters (PDF, Word, Excel with consistent styling)
- Game engines supporting different platforms (PC, Console, Mobile)

## 💡 Solution Approach

The Abstract Factory pattern provides:
- **Family Creation**: Creates entire families of related objects
- **Platform Independence**: Abstracts away platform-specific implementations
- **Consistency Guarantee**: Ensures objects from the same family work together
- **Easy Extension**: Adding new product families requires minimal changes

## 🏗️ Pattern Structure

```
Client → Abstract Factory → Abstract Products
             ↓                    ↑
    ┌─────────┼─────────┐         │
    │                 │         │
Concrete Factory A  Concrete Factory B
    │                 │
    ↓                 ↓
Product Family A   Product Family B
```

## 📁 Pattern Implementations

This directory contains two comprehensive implementations of the Abstract Factory pattern:

### 1. 🖥️ GUI Components System (`guiComponents/`)
Cross-platform GUI component creation for Windows and macOS operating systems.
- **Product Families**: Windows Components, macOS Components
- **Products**: Button, Checkbox
- **Use Case**: Cross-platform desktop application development
- **Key Features**: Platform detection, consistent UI across operating systems

### 2. 🚗 Vehicle Parts Factory (`vehiclePartsFactory/`)
Automotive manufacturing system for creating compatible vehicle component families.
- **Product Families**: Car Parts, Bike Parts, Truck Parts
- **Products**: Engine, Tire, Brake
- **Use Case**: Automotive parts manufacturing and assembly
- **Key Features**: Parts compatibility, type safety, extensible vehicle types

---

## 📱 Implementation 1: GUI Components System

This implementation demonstrates cross-platform GUI component creation for Windows and macOS operating systems.

### Core Components

#### 1. Abstract Products (Interfaces)
```java
public interface Button {
    void paint();
}

public interface Checkbox {
    void paint();
}
```
**Responsibilities:**
- Define contracts for each product type
- Enable polymorphic usage across different platforms
- Ensure consistent interface regardless of implementation

#### 2. Abstract Factory (Interface)
```java
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckBox();
}
```
**Responsibilities:**
- Declares methods for creating each type of abstract product
- Defines the contract that concrete factories must implement
- Ensures consistent method signatures across platforms

#### 3. Concrete Products

##### Windows Components
```java
public class WindowsOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Created Windows OS button");
    }
}

public class WindowsOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Created Windows OS Checkbox");
    }
}
```

##### macOS Components
```java
public class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Created MacOS button");
    }
}

public class MacOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Created MacOS Checkbox");
    }
}
```

#### 4. Concrete Factories

##### Windows Factory
```java
public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsOSButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new WindowsOSCheckbox();
    }
}
```

##### macOS Factory
```java
public class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new MacOSCheckbox();
    }
}
```

#### 5. Application (Client Context)
```java
public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckBox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
```
**Responsibilities:**
- Uses the abstract factory to create product families
- Doesn't know about concrete implementations
- Ensures products from the same family are used together

## 🔧 Key Design Features

### Runtime Platform Detection
The client dynamically determines which factory to use based on the operating system:

```java
String osName = System.getProperty("os.name").toLowerCase();
GUIFactory factory;
if (osName.contains("windows")) {
    factory = new WindowsFactory();
} else if (osName.contains("mac")) {
    factory = new MacOSFactory();
} else {
    throw new RuntimeException("Unsupported OS: " + osName);
}
```

### Family Consistency
- All products created by a factory belong to the same family
- Windows factory creates only Windows-style components
- macOS factory creates only macOS-style components
- No mixing of different platform components

### Extensibility
Adding new products or platforms requires:
1. New abstract product interface
2. Concrete implementations for each platform
3. Update factories to create new products
4. Minimal changes to existing code

---

## 🚗 Implementation 2: Vehicle Parts Factory

This implementation demonstrates automotive manufacturing with compatible vehicle component families.

### System Overview
The Vehicle Parts Factory creates families of compatible automotive components for different vehicle types (Car, Bike, Truck), ensuring that all parts from the same factory work together seamlessly.

### Core Architecture

#### 1. Abstract Products
```java
public interface Engine {
    void start();
    void stop();
    int getHorsepower();
    String getFuelType();
    void performMaintenance();
}

public interface Tire {
    void rotate();
    void brake();
    int getDiameter();
    String getTreadPattern();
    int getMaxPressure();
}

public interface Brake {
    void apply();
    void release();
    String getBrakeType();
    int getMaxForce();
    boolean isAntiLockEnabled();
}
```

#### 2. Abstract Factory
```java
public interface VehicleFactory {
    Engine createEngine();
    Tire createTire();
    Brake createBrake();
    default String getVehicleType() { return "Unknown"; }
}
```

#### 3. Product Families

##### Car Family
- **CarEngine**: 200HP V6 Gasoline with advanced maintenance
- **CarTire**: 17" All-Season for passenger comfort  
- **CarBrake**: Disc brakes with ABS for safety

##### Bike Family  
- **BikeEngine**: 50HP Single-cylinder for efficiency
- **BikeTire**: 19" Sport tires for optimal grip
- **BikeBrake**: Manual disc brakes

##### Truck Family
- **TruckEngine**: 400HP V8 Diesel for heavy-duty performance
- **TruckTire**: 22" Heavy-duty for maximum load capacity
- **TruckBrake**: Air brakes with maximum stopping power

#### 4. Factory Provider & Type Safety
```java
public enum VehicleType {
    CAR("Car", "Four-wheeled passenger vehicle"),
    BIKE("Bike", "Two-wheeled motor vehicle"), 
    TRUCK("Truck", "Heavy-duty cargo vehicle");
}

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

#### 5. Assembly & Client Usage
```java
public class VehicleAssembler {
    private final Engine engine;
    private final Tire tire;
    private final Brake brake;
    
    public VehicleAssembler(VehicleFactory factory) {
        // All parts guaranteed to be from same family
        this.engine = factory.createEngine();
        this.tire = factory.createTire();
        this.brake = factory.createBrake();
    }
    
    public void assemble() {
        engine.start();
        tire.rotate();
        brake.apply();
        // All components work together seamlessly
    }
}
```

### Key Benefits Demonstrated
- **Parts Compatibility**: All components from one factory work together
- **Type Safety**: Enum-based vehicle types prevent runtime errors  
- **Extensibility**: Easy addition of new vehicle types (Electric, Hybrid)
- **Family Consistency**: Guaranteed compatibility within product families
- **Manufacturing Flexibility**: Different production processes per vehicle type

---

## 🧪 Usage Examples

### Basic Cross-Platform Application
```java
public class CrossPlatformApp {
    public static void main(String[] args) {
        // Factory selection based on platform
        GUIFactory factory = PlatformDetector.getFactory();
        
        // Create application with appropriate factory
        Application app = new Application(factory);
        app.paint();
    }
}
```

### Configuration-Based Factory Selection
```java
public class ConfigurableApplication {
    private final GUIFactory factory;
    
    public ConfigurableApplication(String platform) {
        this.factory = switch (platform.toLowerCase()) {
            case "windows" -> new WindowsFactory();
            case "macos" -> new MacOSFactory();
            case "linux" -> new LinuxFactory(); // Future extension
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        };
    }
    
    public void createUI() {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckBox();
        // Use components...
    }
}
```

### Dependency Injection Integration
```java
@Configuration
public class GUIConfiguration {
    
    @Bean
    @ConditionalOnProperty(name = "gui.platform", havingValue = "windows")
    public GUIFactory windowsFactory() {
        return new WindowsFactory();
    }
    
    @Bean
    @ConditionalOnProperty(name = "gui.platform", havingValue = "macos")
    public GUIFactory macosFactory() {
        return new MacOSFactory();
    }
}
```

## 🔄 Extending the System

### Adding Linux Support
```java
// 1. Create Linux-specific products
public class LinuxButton implements Button {
    @Override
    public void paint() {
        System.out.println("Created Linux GTK button");
    }
}

public class LinuxCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Created Linux GTK checkbox");
    }
}

// 2. Create Linux factory
public class LinuxFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new LinuxCheckbox();
    }
}

// 3. Update client code
else if (osName.contains("linux")) {
    factory = new LinuxFactory();
}
```

### Adding New Components
```java
// 1. Define new abstract product
public interface TextField {
    void render();
    void setText(String text);
}

// 2. Update abstract factory
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckBox();
    TextField createTextField(); // New method
}

// 3. Implement in concrete factories
public class WindowsFactory implements GUIFactory {
    // ... existing methods ...
    
    @Override
    public TextField createTextField() {
        return new WindowsTextField();
    }
}
```

### Theme-Based Factories
```java
public interface ThemeFactory extends GUIFactory {
    Color getPrimaryColor();
    Font getDefaultFont();
    String getStylesheet();
}

public class DarkThemeFactory implements ThemeFactory {
    @Override
    public Button createButton() {
        return new DarkButton();
    }
    
    @Override
    public Color getPrimaryColor() {
        return Color.BLACK;
    }
}
```

## ✅ Advantages Demonstrated

1. **Family Consistency**: Ensures related objects work together
2. **Platform Independence**: Code works across different platforms
3. **Easy Extension**: New platforms can be added without changing existing code
4. **Separation of Concerns**: Creation logic separated from business logic
5. **Runtime Flexibility**: Factory can be chosen at runtime
6. **Type Safety**: Compile-time guarantees about product families

## ❌ Trade-offs and Considerations

1. **Complexity**: More complex than simple Factory pattern
2. **Rigid Structure**: Adding new products requires updating all factories
3. **Code Duplication**: Similar structure across concrete factories
4. **Over-engineering**: May be overkill for simple scenarios
5. **Learning Curve**: More difficult to understand than simpler patterns

## 🎓 Interview Deep Dive

### Core Questions

**Q**: What's the difference between Factory and Abstract Factory patterns?  
**A**: Factory creates one type of object, while Abstract Factory creates families of related objects. Abstract Factory ensures products from the same family are used together.

**Q**: When would you use Abstract Factory over Factory Method?  
**A**: When you need to create families of related objects that must be used together, especially in cross-platform applications or when you have multiple product lines.

**Q**: How does Abstract Factory promote consistency?  
**A**: By ensuring all products come from the same factory, it guarantees they belong to the same family and are designed to work together.

### Advanced Scenarios

**Q**: How would you handle factory selection in a web application?  
```java
@Component
public class WebGUIFactoryProvider {
    
    public GUIFactory getFactory(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String theme = request.getParameter("theme");
        
        if (userAgent.contains("Mobile")) {
            return new MobileFactory();
        } else if ("dark".equals(theme)) {
            return new DarkThemeFactory();
        } else {
            return new DefaultFactory();
        }
    }
}
```

**Q**: How would you implement caching for expensive factory objects?  
```java
public class CachedFactoryProvider {
    private static final Map<String, GUIFactory> factoryCache = new ConcurrentHashMap<>();
    
    public static GUIFactory getFactory(String platform) {
        return factoryCache.computeIfAbsent(platform, key -> {
            return switch (key) {
                case "windows" -> new WindowsFactory();
                case "macos" -> new MacOSFactory();
                default -> throw new IllegalArgumentException("Unknown platform: " + key);
            };
        });
    }
}
```

**Q**: How would you handle partial factory implementations?  
```java
public abstract class BaseGUIFactory implements GUIFactory {
    
    @Override
    public Button createButton() {
        return new DefaultButton(); // Common implementation
    }
    
    // Subclasses only override what they need to customize
    public abstract Checkbox createCheckBox();
}
```

## 🌐 Real-World Applications

### GUI Frameworks
- **Swing**: Look and Feel factories (Metal, Windows, Motif)
- **JavaFX**: Platform-specific controls and themes
- **React Native**: Platform-specific component factories

### Database Access
```java
public interface DatabaseFactory {
    Connection createConnection();
    Statement createStatement();
    PreparedStatement createPreparedStatement(String sql);
}

public class MySQLFactory implements DatabaseFactory {
    // MySQL-specific implementations
}

public class PostgreSQLFactory implements DatabaseFactory {
    // PostgreSQL-specific implementations
}
```

### Document Processing
```java
public interface DocumentFactory {
    TextProcessor createTextProcessor();
    ImageProcessor createImageProcessor();
    TableProcessor createTableProcessor();
}

public class PDFDocumentFactory implements DocumentFactory {
    // PDF-specific processors
}

public class WordDocumentFactory implements DocumentFactory {
    // Word-specific processors
}
```

## 📊 Pattern Comparison

| Aspect | Simple Factory | Factory Method | Abstract Factory |
|--------|---------------|----------------|------------------|
| **Complexity** | Low | Medium | High |
| **Flexibility** | Limited | Good | Excellent |
| **Product Types** | Single | Single | Multiple (Family) |
| **Extensibility** | Hard | Easy | Medium |
| **Use Case** | Simple creation | Subclass creation | Family creation |
| **Example** | NotificationFactory | DocumentCreator | GUIFactory |

## 🔧 Running the Example

```bash
# Compile the GUI components system
javac guiComponents/*.java

# Run the demonstration
java guiComponents.Client
```

**Expected Output on Windows:**
```
Created Windows OS button
Created Windows OS Checkbox
```

**Expected Output on macOS:**
```
Created MacOS button
Created MacOS Checkbox
```

## 🚀 Production Enhancements

### Factory Registry
```java
public class FactoryRegistry {
    private static final Map<String, Supplier<GUIFactory>> factories = new HashMap<>();
    
    static {
        registerFactory("windows", WindowsFactory::new);
        registerFactory("macos", MacOSFactory::new);
        registerFactory("linux", LinuxFactory::new);
    }
    
    public static void registerFactory(String platform, Supplier<GUIFactory> factory) {
        factories.put(platform, factory);
    }
    
    public static GUIFactory getFactory(String platform) {
        Supplier<GUIFactory> factorySupplier = factories.get(platform);
        if (factorySupplier == null) {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
        return factorySupplier.get();
    }
}
```

### Configuration-Driven Factories
```java
@ConfigurationProperties(prefix = "gui")
public class GUIConfiguration {
    private String platform;
    private String theme;
    private Map<String, String> customizations;
    
    // getters and setters
}

@Service
public class ConfigurableGUIFactory {
    
    @Autowired
    private GUIConfiguration config;
    
    public GUIFactory createFactory() {
        GUIFactory baseFactory = getBasePlatformFactory();
        
        if (config.getTheme() != null) {
            baseFactory = new ThemeDecorator(baseFactory, config.getTheme());
        }
        
        return baseFactory;
    }
}
```

## 🔗 Related Patterns

- **Factory Method**: Simpler version for single products
- **Builder**: For complex object construction
- **Prototype**: Alternative creation mechanism
- **Singleton**: Factories are often implemented as singletons
- **Bridge**: Similar structure for abstraction and implementation separation

---

💡 **Key Takeaway**: Abstract Factory excels when you need to create families of related objects that must work together, providing consistency and platform independence while maintaining flexibility for future extensions.