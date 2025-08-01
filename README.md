# 🎯 Low-Level System Design Resource

[![Java](https://img.shields.io/badge/Java-24-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](LICENSE)

A comprehensive repository for preparing for Low-Level System Design interviews. This repository contains implementations of popular design patterns, mock interview problems, and best practices for object-oriented design.

## 📚 Table of Contents

- [🎯 Overview](#-overview)
- [🏗️ Repository Structure](#️-repository-structure)
- [🔧 Prerequisites](#-prerequisites)
- [🚀 Getting Started](#-getting-started)
- [📖 Content Overview](#-content-overview)
- [🎨 Design Patterns](#-design-patterns)
- [🧪 Mock Interviews](#-mock-interviews)
- [📝 Contributing](#-contributing)
- [📄 License](#-license)

## 🎯 Overview

This repository serves as a comprehensive study guide for Low-Level Design (LLD) interviews. It includes:

- **Design Patterns**: Complete implementations with explanations
- **Mock Interview Problems**: Real-world problems with solutions
- **Best Practices**: Object-oriented design principles
- **Code Examples**: Well-documented, production-ready code

## 🏗️ Repository Structure

```
src/main/java/com/basavarajpatil/
├── designPatterns/           # Design pattern implementations
│   ├── creational/          # Creational design patterns
│   │   ├── factory/         # Factory pattern examples
│   │   │   ├── notificationSystem/  # Multi-channel notifications
│   │   │   └── PaymentSystem/       # Multi-method payments
│   │   ├── abstractFactory/ # Abstract Factory pattern examples
│   │   │   ├── guiComponents/       # Cross-platform GUI components
│   │   │   └── vehiclePartsFactory/ # Automotive manufacturing system
│   │   ├── builder/         # Builder pattern examples
│   │   │   └── mealOrderingSystem/  # Restaurant meal construction
│   │   └── Singleton/       # Singleton pattern examples
│   │       ├── DatabaseConnection/  # DB connection management
│   │       ├── Logger/             # Centralized logging
│   │       └── ServiceRegistry/    # Service discovery
│   ├── structuralPatterns/  # Structural design patterns
│   │   ├── adapter/         # Adapter pattern examples
│   │   │   └── paymentGateway/     # Multi-provider payment integration
│   │   └── decorator/       # Decorator pattern examples
│   │       └── notificationSystem/ # Multi-channel notification system
│   └── behaviouralPatterns/ # Behavioral design patterns (coming soon)
└── lldMockInterviews/       # Mock interview problems
    └── easy/               # Easy-level problems
        ├── vendingMachine/     # Vending machine implementation
        └── documentReaderSystem/ # Document processing system
```

## 🔧 Prerequisites

- Java 24 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/basupatil1213/low-level-system-design-resource.git
   cd low-level-system-design-resource
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run examples**
   ```bash
   # Run specific examples
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.factory.notificationSystem.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.factory.PaymentSystem.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.abstractFactory.vehiclePartsFactory.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.lldMockInterviews.easy.vendingMachine.Client"
   mvn exec:java -Dexec.mainClass="com.basavarajpatil.lldMockInterviews.easy.documentReaderSystem.Client"
   ```

## 📖 Content Overview

### 🎨 Design Patterns

#### Creational Patterns
- **Factory Pattern**: 
  - Notification system implementation (email, SMS)
  - Payment system implementation (UPI, PayPal, Credit Card)
- **Abstract Factory Pattern**: 
  - Cross-platform GUI components (Windows, macOS)
  - Vehicle parts manufacturing system (Car, Bike, Truck families)
- **Builder Pattern**:
  - Restaurant meal ordering system with step-by-step construction
  - Fluent interface, validation, and Director pattern integration
- **Singleton Pattern**: Database connection, Logger, Service registry

#### Structural Patterns
- **Adapter Pattern**:
  - Payment gateway integration (Stripe, PayPal, Square)
  - Multi-provider API adaptation with unified interface
- **Decorator Pattern**:
  - Multi-channel notification system (Email, SMS, Slack)
  - Dynamic behavior composition with validation chains

### 🧪 Mock Interviews

#### Easy Level
- **Vending Machine**: Complete implementation with inventory management, payment processing, and item dispensing
- **Document Reader System**: Multi-format document processing demonstrating Factory and Singleton patterns

## 🎯 Learning Path

1. **Start with Design Patterns**: Understand fundamental patterns
2. **Practice Mock Problems**: Apply patterns to real-world scenarios
3. **Review Best Practices**: Focus on SOLID principles
4. **Iterate and Improve**: Refactor code for better design

## 📈 Interview Preparation Tips

- **Understand Requirements**: Always clarify requirements before coding
- **Start Simple**: Begin with basic functionality, then add complexity
- **Think About Scale**: Consider scalability and extensibility
- **Code Quality**: Write clean, readable, and maintainable code
- **Test Your Code**: Think about edge cases and error handling
- **Compare Approaches**: Understand different implementation strategies (e.g., string-based vs enum-based factories)

## 🔍 Key Learning Comparisons

### Design Pattern Categories
| Category | Purpose | Complexity | Examples in Repo |
|----------|---------|------------|------------------|
| **Creational** | Object creation mechanisms | ⭐⭐⭐☆☆ | Factory, Abstract Factory, Builder, Singleton |
| **Structural** | Object composition & relationships | ⭐⭐⭐⭐☆ | Adapter, Decorator |
| **Behavioral** | Communication between objects | ⭐⭐⭐⭐⭐ | Coming Soon |

### Creational Patterns Evolution
| Aspect | Factory Pattern | Abstract Factory | Builder Pattern | Singleton Pattern |
|--------|----------------|------------------|-----------------|-------------------|
| **Purpose** | Create single objects | Create object families | Step-by-step construction | Single instance management |
| **Complexity** | ⭐⭐☆☆☆ | ⭐⭐⭐⭐☆ | ⭐⭐⭐⭐☆ | ⭐⭐⭐☆☆ |
| **Flexibility** | Medium | High | Very High | Low |
| **Use Case** | Multiple implementations | Platform-specific families | Complex object construction | Resource management |
| **Example** | NotificationFactory | GUIFactory | MealBuilder | DatabaseConnection |

### Factory Pattern Implementations
| Aspect | Notification System | Payment System |
|--------|-------------------|----------------|
| **Type Safety** | String-based (runtime validation) | Enum-based (compile-time validation) |
| **Error Handling** | Runtime exceptions for invalid types | Compile-time prevention of invalid types |
| **Extensibility** | Manual string constants | Type-safe enum additions |
| **IDE Support** | Limited auto-completion | Full IntelliSense support |
| **Best For** | Simple, flexible systems | Type-critical financial systems |

### Builder Pattern Implementation
| Aspect | Meal Ordering System |
|--------|---------------------|
| **Construction Type** | Step-by-step with validation |
| **Interface Style** | Fluent method chaining |
| **Patterns Combined** | Builder + Director + Abstract Factory |
| **Complexity** | ⭐⭐⭐⭐☆ (High) |
| **Key Features** | Immutable products, validation chains, multiple meal types |
| **Meal Types** | Kids meals, Deluxe meals with premium ingredients |
| **Validation** | Complete meal checking, nutritional analysis |
| **Director Integration** | Standardized construction processes |
| **Error Handling** | State validation, required field checking |
| **Extensibility** | Easy to add new meal types and menu items |
| **Real-world Value** | Restaurant ordering systems, complex configuration builders |

### Structural Patterns Implementation
| Aspect | Payment Gateway Adapter | Notification System Decorator |
|--------|------------------------|--------------------------------|
| **Challenge** | API incompatibility between providers | Adding multiple notification channels dynamically |
| **Solution** | Unified PaymentProcessor interface | Composable NotifierDecorator chain |
| **Complexity** | ⭐⭐⭐⭐☆ (High) | ⭐⭐⭐⭐⭐ (Very High) |
| **Key Features** | Parameter mapping, unit conversion, error handling | Dynamic composition, validation chains, priority handling |
| **Providers/Channels** | Stripe, PayPal, Square | Email, SMS, Slack + Base Logging |
| **Pattern Benefits** | Unified interface, easy provider switching | Runtime behavior composition, flexible combinations |
| **Real-world Value** | Production-ready payment integration | Enterprise notification infrastructure |

### Mock Interview Problems Progression
| Aspect | Vending Machine | Document Reader System |
|--------|----------------|------------------------|
| **Complexity** | ⭐⭐⭐☆☆ (Medium) | ⭐⭐⭐⭐☆ (Medium-High) |
| **Patterns Used** | Encapsulation, State Management | Factory, Singleton, Strategy |
| **Key Focus** | Object interaction, Error handling | Pattern combination, File I/O |
| **Real-world I/O** | Simulated operations | Actual file reading (TXT) |
| **Extensibility** | Adding new products/slots | Adding new document formats |
| **Interview Level** | Junior to Mid-level | Mid to Senior level |

This comparison helps understand when to choose different implementation approaches based on your system's requirements.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🎓 Resources

- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Clean Code: A Handbook of Agile Software Craftsmanship](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350884)
- [System Design Interview](https://www.amazon.com/System-Design-Interview-insiders-Second/dp/B08CMF2CQF)

---

⭐ Star this repository if you find it helpful for your interview preparation!
