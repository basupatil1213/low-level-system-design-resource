# 📧 Notification System - Factory Pattern

A comprehensive implementation of the Factory Design Pattern through a notification system that supports multiple communication channels.

## 🎯 Problem Statement

In modern applications, you need to send notifications through various channels (email, SMS, push notifications, etc.) without tightly coupling your code to specific notification implementations. The system should:

- Support multiple notification types
- Allow easy addition of new notification channels
- Hide creation complexity from clients
- Maintain consistency across notification types

## 💡 Solution Approach

The Factory pattern provides an interface for creating notification objects without specifying their exact classes. This promotes:
- **Loose coupling** between client code and notification implementations
- **Easy extensibility** for adding new notification types
- **Centralized creation logic** for better maintainability

## 🏗️ Architecture Overview

```
Client → NotificationFactory → Notification Interface
                                      ↑
                              ┌───────┼───────┐
                              │       │       │
                    EmailNotification │ SMSNotification
                                      │
                              PushNotification
                                 (future)
```

## 📁 Implementation Components

### 1. Notification Interface
```java
public interface Notification {
    void send(String message);
}
```
**Responsibility**: Defines the contract for all notification types

### 2. EmailNotification (Concrete Product)
```java
public class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email Notification: " + message);
    }
}
```
**Responsibility**: Implements email-specific notification logic

### 3. SMSNotification (Concrete Product)
```java
public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
```
**Responsibility**: Implements SMS-specific notification logic

### 4. NotificationFactory (Factory)
```java
public class NotificationFactory {
    public static Notification getNotification(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailNotification();
        }
        if (type.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        }
        throw new IllegalArgumentException("Notification type not supported: " + type);
    }
}
```
**Responsibility**: Creates appropriate notification objects based on type

### 5. Client (Consumer)
**Responsibility**: Uses the factory to create and send notifications

## 🔧 Key Design Decisions

### Factory Method Approach
- **Static Factory Method**: Simple approach for basic use cases
- **String-based Type**: Easy to use and understand
- **Exception Handling**: Clear error messages for unsupported types
- **Case Insensitive**: Flexible input handling

### Error Handling Strategy
- **IllegalArgumentException**: For unsupported notification types
- **Descriptive Messages**: Clear indication of what went wrong
- **Fail Fast**: Immediate feedback on invalid input

## 🧪 Usage Examples

### Basic Usage
```java
// Send email notification
Notification emailNotification = NotificationFactory.getNotification("email");
emailNotification.send("Welcome to our platform!");

// Send SMS notification
Notification smsNotification = NotificationFactory.getNotification("sms");
smsNotification.send("Your verification code is: 123456");
```

### With Error Handling
```java
try {
    Notification notification = NotificationFactory.getNotification("push");
    notification.send("New message received");
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
    // Fallback to default notification type
    Notification fallback = NotificationFactory.getNotification("email");
    fallback.send("New message received");
}
```

### Bulk Notifications
```java
public class NotificationService {
    public void sendBulkNotifications(String message, List<String> types) {
        for (String type : types) {
            try {
                Notification notification = NotificationFactory.getNotification(type);
                notification.send(message);
            } catch (IllegalArgumentException e) {
                System.out.println("Skipping unsupported type: " + type);
            }
        }
    }
}

// Usage
NotificationService service = new NotificationService();
service.sendBulkNotifications("System maintenance tonight", 
    Arrays.asList("email", "sms", "push"));
```

## 🔄 Extending the System

### Adding Push Notifications
```java
// 1. Create new concrete product
public class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Push Notification: " + message);
    }
}

// 2. Update factory
public static Notification getNotification(String type) {
    switch (type.toLowerCase()) {
        case "email": return new EmailNotification();
        case "sms": return new SMSNotification();
        case "push": return new PushNotification();
        default: throw new IllegalArgumentException("Notification type not supported: " + type);
    }
}
```

### Configuration-Based Factory
```java
public class ConfigurableNotificationFactory {
    private static final Map<String, Class<? extends Notification>> notificationTypes = new HashMap<>();
    
    static {
        notificationTypes.put("email", EmailNotification.class);
        notificationTypes.put("sms", SMSNotification.class);
    }
    
    public static Notification getNotification(String type) throws Exception {
        Class<? extends Notification> clazz = notificationTypes.get(type.toLowerCase());
        if (clazz == null) {
            throw new IllegalArgumentException("Notification type not supported: " + type);
        }
        return clazz.getDeclaredConstructor().newInstance();
    }
}
```

## ✅ Benefits Demonstrated

1. **Loose Coupling**: Client doesn't know about concrete notification classes
2. **Easy Extension**: New notification types can be added without changing client code
3. **Centralized Logic**: All creation logic in one place
4. **Polymorphism**: All notifications treated uniformly through interface
5. **Code Reusability**: Factory can be used across different parts of application

## ❌ Trade-offs

1. **Additional Complexity**: More classes and interfaces to maintain
2. **String Dependency**: Type parameter is string-based (could use enums)
3. **Static Factory**: Cannot be easily mocked or extended
4. **No Parameterization**: Cannot pass constructor parameters easily

## 🎓 Interview Deep Dive

### Common Follow-up Questions

**Q**: How would you make this factory more flexible?  
**A**: Use reflection, configuration files, or dependency injection to register notification types dynamically.

**Q**: What if notifications need different constructor parameters?  
**A**: Use Abstract Factory pattern or Builder pattern for complex object creation.

**Q**: How would you handle notification failures?  
**A**: Implement retry logic, fallback mechanisms, or observer pattern for status tracking.

**Q**: How would you test this factory?  
**A**: Create mock implementations of Notification interface and test factory behavior with different inputs.

### Advanced Scenarios

**Q**: Design a notification system with priority levels  
**A**: Add priority parameter to send() method or create separate interfaces for different priority levels.

**Q**: How would you handle async notifications?  
**A**: Return CompletableFuture from send() method or use observer pattern with callback mechanisms.

**Q**: Scale this for millions of notifications  
**A**: Implement queue-based processing, batch notifications, and proper resource management.

## 🔗 Pattern Variations

### Abstract Factory
For creating families of related notifications:
```java
public interface NotificationAbstractFactory {
    Notification createUrgentNotification();
    Notification createRegularNotification();
}
```

### Factory Method
For subclass-specific creation:
```java
public abstract class NotificationCreator {
    public abstract Notification createNotification();
    
    public void sendNotification(String message) {
        Notification notification = createNotification();
        notification.send(message);
    }
}
```

## 🚀 Production Considerations

1. **Configuration**: Use external config files for notification types
2. **Validation**: Validate message content and format
3. **Rate Limiting**: Prevent notification spam
4. **Monitoring**: Track delivery success/failure rates
5. **Security**: Encrypt sensitive notification content
6. **Fallback**: Implement backup notification channels

---

💡 **Key Takeaway**: Factory pattern shines when you have multiple implementations of the same interface and want to decouple object creation from usage.
