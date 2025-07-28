# 🔒 Singleton Design Pattern

The Singleton Design Pattern ensures that a class has only one instance and provides a global point of access to that instance. It's one of the most commonly used (and sometimes misused) design patterns.

## 🎯 Problem Statement

Sometimes you need exactly one instance of a class throughout the application lifecycle. Examples include:
- Database connections
- Logger instances
- Configuration managers
- Thread pools
- Cache managers

## 💡 Solution

The Singleton pattern restricts instantiation of a class to a single object by:
1. Making the constructor private
2. Providing a static method to get the instance
3. Storing the instance in a static variable

## 🏗️ Structure

```
Singleton Class
├── private constructor
├── private static instance
└── public static getInstance() method
```

## 📁 Implementations

### 1. Database Connection (`DatabaseConnection`)
- **Purpose**: Manages database connectivity
- **Implementation**: Bill Pugh Singleton (Initialization-on-demand)
- **Features**: Connect, disconnect, and query operations
- **Thread-Safe**: Yes

### 2. Logger (`Logger` & `LoggerBillPughSingleton`)
- **Purpose**: Centralized logging functionality
- **Implementations**: 
  - Thread-safe with double-checked locking
  - Bill Pugh Singleton approach
- **Features**: Info, debug, error, and warn logging levels
- **Thread-Safe**: Yes

### 3. Service Registry (`ServiceRegistry`)
- **Purpose**: Central registry for application services
- **Implementation**: Bill Pugh Singleton
- **Features**: Register, unregister, and lookup services
- **Thread-Safe**: Yes (uses ConcurrentHashMap)

## 🔧 Implementation Approaches

### 1. Eager Initialization
```java
public class Singleton {
    private static final Singleton instance = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return instance; }
}
```

### 2. Lazy Initialization (Thread-Safe)
```java
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### 3. Bill Pugh Singleton (Recommended)
```java
public class Singleton {
    private Singleton() {}
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

## ✅ Advantages

1. **Controlled Access**: Only one instance exists
2. **Global Access Point**: Accessible from anywhere in the application
3. **Lazy Initialization**: Instance created only when needed
4. **Memory Efficient**: Saves memory by preventing multiple instances
5. **Configuration Management**: Perfect for global settings

## ❌ Disadvantages

1. **Global State**: Can introduce hidden dependencies
2. **Testing Difficulties**: Hard to mock or test in isolation
3. **Concurrency Issues**: Requires careful thread-safety consideration
4. **Violation of SRP**: Class controls both its behavior and instantiation
5. **Hidden Dependencies**: Makes dependencies less explicit

## 🎯 When to Use

✅ **Good Use Cases:**
- Database connection pools
- Logging services
- Configuration managers
- Cache managers
- Thread pools

❌ **Avoid When:**
- You need multiple instances in the future
- The class has mutable state shared across threads
- Testing is critical and mocking is required
- The object is stateless (use static methods instead)

## 🧪 Running Examples

### Database Connection Example
```bash
javac DatabaseConnection/*.java
java DatabaseConnection.Client
```

### Logger Example
```bash
javac Logger/*.java
java Logger.Client
```

### Service Registry Example
```bash
javac ServiceRegistry/*.java
java ServiceRegistry.Client
```

## 🔒 Thread Safety Considerations

| Implementation | Thread-Safe | Performance | Lazy Loading |
|----------------|-------------|-------------|--------------|
| Eager | ✅ | High | ❌ |
| Synchronized Method | ✅ | Low | ✅ |
| Double-Checked Locking | ✅ | High | ✅ |
| Bill Pugh | ✅ | High | ✅ |
| Enum | ✅ | High | ❌ |

## 🎓 Interview Questions

1. **Q**: What is the Singleton Design Pattern?
   **A**: A pattern that ensures only one instance of a class exists and provides global access to it.

2. **Q**: How do you make Singleton thread-safe?
   **A**: Use synchronized methods, double-checked locking, or Bill Pugh singleton approach.

3. **Q**: What are the problems with Singleton?
   **A**: Global state, testing difficulties, hidden dependencies, and potential concurrency issues.

4. **Q**: How do you test a Singleton class?
   **A**: Use dependency injection, reflection to reset instance, or redesign to avoid Singleton.

5. **Q**: What's the difference between Singleton and static class?
   **A**: Singleton can implement interfaces, be subclassed, and provides lazy loading; static classes cannot.

## 🔄 Alternatives to Singleton

1. **Dependency Injection**: Use DI containers to manage single instances
2. **Static Classes**: For stateless utility functions
3. **Registry Pattern**: For managing multiple named instances
4. **Monostate Pattern**: Multiple instances sharing the same state

## 🔗 Related Patterns

- **Factory**: Often implemented as Singletons
- **Abstract Factory**: Factory instances are often Singletons
- **Builder**: Director can be a Singleton
- **Facade**: Facade objects are often Singletons