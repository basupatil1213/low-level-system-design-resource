# Structural Patterns

Structural patterns deal with object composition and relationships between entities. They help ensure that when one part of a system changes, the entire structure doesn't need to change.

## 📋 Overview

Structural patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient. They compose interfaces or implementations to realize new functionality.

## 🎯 Key Characteristics

- **Composition over Inheritance**: Focus on object composition rather than class inheritance
- **Interface Simplification**: Provide simplified interfaces to complex subsystems
- **Flexible Architecture**: Allow for flexible and maintainable system architectures
- **Decoupling**: Reduce dependencies between system components

## 📂 Implemented Patterns

### 1. Adapter Pattern
**Location**: `adapter/paymentGateway/`

**Purpose**: Allows incompatible interfaces to work together by converting the interface of a class into another interface that clients expect.

**Key Components**:
- **Target Interface**: `PaymentProcessor` - The interface expected by clients
- **Adaptee**: External payment SDKs (PayPal, Square) with incompatible interfaces
- **Adapter**: `PayPalAdapter`, `SquareAdapter` - Converts adaptee's interface to target interface
- **Client**: Uses the target interface without knowing about adaptation

**Real-world Applications**:
- Payment gateway integration
- Database driver adaptation
- Legacy system integration
- Third-party API integration

**Interview Focus Areas**:
- Understanding interface incompatibility problems
- Implementing object vs class adapters
- Handling complex parameter transformations
- Managing different error handling mechanisms

### 2. Decorator Pattern
**Location**: `decorator/notificationSystem/`

**Purpose**: Attach additional responsibilities to objects dynamically without altering their structure.

**Key Components**:
- **Component Interface**: `Notifier` - The interface for objects that can have responsibilities added
- **Concrete Component**: `BaseNotifier` - Basic notification with logging functionality  
- **Decorator**: `NotifierDecorator` - Abstract class that wraps and delegates to components
- **Concrete Decorators**: `EmailNotifier`, `SMSNotifier`, `SlackNotifier` - Add specific notification channels

**Real-world Applications**:
- Multi-channel notification systems
- Request/response processing pipelines
- Stream processing (compression, encryption, buffering)
- UI component enhancement (borders, scrollbars, shadows)

**Interview Focus Areas**:
- Dynamic behavior addition vs static inheritance
- Decorator chain composition and order dependencies
- Maintaining interface consistency across decorators
- Handling validation and error propagation in chains

---

## 🏗️ Pattern Categories

### Object Structural Patterns
- **Adapter**: Convert interface of a class into another interface
- **Bridge**: Separate abstraction from implementation
- **Composite**: Compose objects into tree structures
- **Decorator**: Add behavior to objects dynamically
- **Facade**: Provide simplified interface to complex subsystem
- **Flyweight**: Share common state to support large numbers of objects
- **Proxy**: Provide placeholder/surrogate for another object

### Class Structural Patterns
- **Adapter (Class Version)**: Use inheritance to adapt interfaces

---

## 🎓 Learning Path

### Beginner Level
1. **Adapter Pattern** - Start here to understand basic structural adaptation
2. **Facade Pattern** - Learn about interface simplification
3. **Decorator Pattern** - Understand dynamic behavior addition

### Intermediate Level
4. **Composite Pattern** - Master tree structures and part-whole hierarchies
5. **Bridge Pattern** - Separate abstraction from implementation
6. **Proxy Pattern** - Control access and add functionality

### Advanced Level
7. **Flyweight Pattern** - Optimize memory usage for large object collections

---

## 💡 Common Interview Questions

### Conceptual Questions
1. **"What are structural patterns and how do they differ from creational and behavioral patterns?"**
2. **"When would you choose Adapter over Facade pattern?"**
3. **"Explain the difference between object adapter and class adapter."**
4. **"How does Decorator pattern maintain the Open/Closed Principle?"**

### Implementation Questions
1. **"Implement an adapter to integrate two incompatible payment systems"**
2. **"Design a decorator chain for processing HTTP requests"**
3. **"Create a composite structure for a file system"**
4. **"Implement a proxy with caching and access control"**

### Design Questions
1. **"Design a plugin architecture using structural patterns"**
2. **"How would you handle multiple data source integration?"**
3. **"Design a UI component library with flexible styling"**
4. **"Create a caching layer that can work with different storage systems"**

---

## 🔧 Best Practices

### Design Principles
- **Single Responsibility**: Each adapter/decorator should have one reason to change
- **Open/Closed**: Open for extension, closed for modification
- **Liskov Substitution**: Adapters should be substitutable for original interfaces
- **Interface Segregation**: Create focused, specific interfaces
- **Dependency Inversion**: Depend on abstractions, not concretions

### Implementation Guidelines
- **Keep adapters focused**: One adapter per interface incompatibility
- **Use composition**: Prefer object composition over inheritance
- **Handle errors gracefully**: Maintain consistent error handling across adapters
- **Document transformations**: Clearly document parameter and response mappings
- **Test thoroughly**: Verify both success and failure scenarios

### Common Pitfalls to Avoid
- **Over-adaptation**: Don't create adapters for simple interface differences
- **Tight coupling**: Avoid making adapters dependent on specific implementations
- **Performance overhead**: Be mindful of adaptation performance costs
- **Complex chains**: Keep decorator/adapter chains manageable
- **Missing validation**: Always validate inputs in adapters

---

## 📚 Related Patterns

### Pattern Relationships
- **Adapter + Strategy**: Adapt different algorithms to common interface
- **Decorator + Composite**: Build complex structures with enhanced behavior
- **Facade + Adapter**: Simplify and adapt complex subsystems
- **Proxy + Decorator**: Control access while adding functionality

### Pattern Combinations
```java
// Adapter + Strategy Example
PaymentProcessor processor = new PayPalAdapter(new PayPalSDK());
PaymentStrategy strategy = new CreditCardStrategy(processor);

// Decorator + Observer Example
Logger logger = new TimestampDecorator(
    new FileLogger(new ConsoleLogger())
);
```

---

## 🚀 Next Steps

1. **Implement Additional Patterns**: Add Decorator, Facade, Composite patterns
2. **Create Complex Examples**: Build real-world applications using multiple patterns
3. **Performance Analysis**: Compare different structural approaches
4. **Integration Testing**: Test pattern combinations in larger systems

---

## 📖 Resources

- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
- [Structural Pattern Examples](https://refactoring.guru/design-patterns/structural-patterns)
- [Java Design Pattern Best Practices](https://docs.oracle.com/javase/tutorial/java/javaOO/)

---

*This module is part of the Low-Level Design (LLD) practice repository for technical interview preparation.*
