# 📝 Logger Singleton

Two implementations of the Singleton pattern for centralized logging functionality, demonstrating different approaches to thread safety and initialization.

## 🎯 Use Case

Logging is a cross-cutting concern that requires:
- Single point of configuration
- Consistent formatting across the application
- Thread-safe access from multiple components
- Centralized log level management
- Global accessibility

## 🏗️ Implementation Approaches

### 1. Double-Checked Locking (`Logger`)
```java
private static volatile Logger logger;

public static Logger getInstance() {
    if (logger == null) {
        synchronized (Logger.class) {
            if (logger == null) {
                logger = new Logger();
            }
        }
    }
    return logger;
}
```

**Features:**
- **Lazy initialization**: Created only when first accessed
- **Thread safety**: Uses double-checked locking pattern
- **Performance**: Synchronization only during initialization
- **Volatile**: Ensures visibility across threads

### 2. Bill Pugh Singleton (`LoggerBillPughSingleton`)
```java
private static class LoggerBillPughSingletonHolder {
    private static final LoggerBillPughSingleton instance = new LoggerBillPughSingleton();
}

public static LoggerBillPughSingleton getInstance() {
    return LoggerBillPughSingletonHolder.instance;
}
```

**Features:**
- **Lazy initialization**: Instance created when holder class is loaded
- **Thread safety**: Guaranteed by JVM class loading mechanism
- **Performance**: No synchronization overhead
- **Clean**: Simple and elegant implementation

## 🔧 Logging Levels

### Available Methods
- **`info(String message)`**: General information
- **`debug(String message)`**: Debug information for development
- **`warn(String message)`**: Warning messages
- **`error(String message)`**: Error conditions

### Output Format
```
[LEVEL] message
```

Example:
```
[INFO] User logged in successfully
[DEBUG] Database query executed in 150ms
[WARN] High memory usage detected
[ERROR] Failed to connect to external service
```

## 💡 Design Comparison

| Aspect | Double-Checked Locking | Bill Pugh |
|--------|----------------------|-----------|
| Thread Safety | ✅ Explicit synchronization | ✅ JVM guarantees |
| Performance | ⚡ Good (sync only once) | ⚡ Excellent (no sync) |
| Complexity | 🔶 Moderate | 🟢 Simple |
| Memory Usage | 🔶 Volatile keyword overhead | 🟢 No overhead |
| Lazy Loading | ✅ Yes | ✅ Yes |
| Recommended | ✅ Good | ⭐ Preferred |

## 🧪 Usage Examples

### Basic Logging
```java
Logger logger = Logger.getInstance();

logger.info("Application started");
logger.debug("Processing user request: " + userId);
logger.warn("Low disk space: " + freeSpace + "MB");
logger.error("Database connection failed: " + exception.getMessage());
```

### In Different Classes
```java
public class UserService {
    private static final Logger logger = Logger.getInstance();
    
    public void createUser(User user) {
        logger.info("Creating user: " + user.getEmail());
        try {
            // User creation logic
            logger.info("User created successfully");
        } catch (Exception e) {
            logger.error("Failed to create user: " + e.getMessage());
        }
    }
}

public class PaymentService {
    private static final Logger logger = Logger.getInstance();
    
    public void processPayment(Payment payment) {
        logger.info("Processing payment: " + payment.getId());
        // Payment processing logic
    }
}
```

## ✅ Advantages

1. **Centralized Logging**: Single point for all logging operations
2. **Global Access**: Available from any part of the application
3. **Thread Safety**: Safe for concurrent use
4. **Consistent Format**: Uniform log message formatting
5. **Memory Efficient**: Single instance across application
6. **Configuration**: Single place to configure logging behavior

## ❌ Limitations

1. **Testing Challenges**: Hard to mock or test in isolation
2. **Global State**: Introduces shared state across application
3. **Inflexibility**: Cannot have different loggers for different modules
4. **Configuration**: Hard to change logging configuration at runtime

## 🔄 Production Considerations

### Real-World Improvements
```java
public class ProductionLogger {
    private final String logLevel;
    private final PrintWriter fileWriter;
    
    public void log(LogLevel level, String message) {
        if (shouldLog(level)) {
            String formatted = formatMessage(level, message);
            writeToFile(formatted);
            writeToConsole(formatted);
        }
    }
    
    private String formatMessage(LogLevel level, String message) {
        return String.format("[%s] %s - %s", 
            LocalDateTime.now(), level, message);
    }
}
```

### Better Alternatives
- **SLF4J + Logback**: Industry standard logging framework
- **Log4j2**: High-performance logging library
- **Java Util Logging**: Built-in Java logging

## 🎓 Interview Questions

**Q**: Why use Singleton for logging?  
**A**: Provides centralized configuration, consistent formatting, and global access while ensuring single instance for memory efficiency.

**Q**: Which implementation is better and why?  
**A**: Bill Pugh is preferred due to simplicity, no synchronization overhead, and JVM-guaranteed thread safety.

**Q**: How would you test a class that uses this logger?  
**A**: Use dependency injection instead of directly accessing singleton, or use logging frameworks that support mocking.

**Q**: What are the issues with this logging approach?  
**A**: Global state, testing difficulties, lack of configurability, and inability to have different loggers for different modules.

**Q**: How would you improve this for production use?  
**A**: Add log levels, file writing, rotation, configuration, formatting options, and consider using established logging frameworks.

## 🔗 Related Patterns

- **Factory**: For creating different types of loggers
- **Observer**: For notifying multiple log handlers
- **Strategy**: For different logging strategies (file, console, network)
- **Decorator**: For adding features like timestamps, formatting
