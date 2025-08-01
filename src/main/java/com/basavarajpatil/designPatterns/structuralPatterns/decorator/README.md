# 🎨 Decorator Pattern

The Decorator pattern allows behavior to be added to objects dynamically without altering their structure. This pattern creates a decorator class that wraps the original class and provides additional functionality while keeping the class methods' signature intact.

## 📂 Implementations

### 🔔 Notification System
**Location**: `notificationSystem/`

A comprehensive implementation demonstrating multi-channel notification system using decorator pattern.

**Key Features**:
- **Dynamic Composition**: Add notification channels at runtime
- **Chain Validation**: Each decorator validates its specific requirements
- **Graceful Degradation**: Falls back to wrapped notifier on failure
- **Priority Handling**: Different processing based on notification priority
- **Error Handling**: Comprehensive error handling and reporting

**Channels Implemented**:
- **BaseNotifier**: Core logging functionality
- **EmailNotifier**: Email notifications with SMTP simulation
- **SMSNotifier**: SMS notifications with character limits
- **SlackNotifier**: Slack messages with formatting

**Usage Example**:
```java
// Create multi-channel notification system
Notifier multiChannel = new SlackNotifier(
    new SMSNotifier(
        new EmailNotifier(
            new BaseNotifier("AlertSystem")
        )
    )
);

// Send urgent notification
NotificationResult result = multiChannel.send(
    "Critical system alert!", 
    NotificationMetadata.urgent("admin@company.com", "System Alert")
);
```

## 🎯 Pattern Benefits

1. **Flexibility**: Add/remove responsibilities at runtime
2. **Single Responsibility**: Each decorator has one focused purpose
3. **Open/Closed Principle**: Open for extension, closed for modification
4. **Composition over Inheritance**: Avoids class explosion

## 🔧 When to Use

- Need to add responsibilities to objects dynamically
- Want to avoid creating many subclasses
- Behaviors can be combined in various ways
- Need to maintain existing interfaces

## 📚 Learning Resources

- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
- [Decorator Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/decorator)

---

*Part of the Low-Level Design (LLD) practice repository for technical interview preparation.*
