# 🎨 Decorator Pattern - Notification System

A comprehensive implementation of the **Decorator Pattern** using a real-world notification system. This example demonstrates how to dynamically add new notification channels (Email, SMS, Slack) to a base logging system without modifying existing code.

## 📋 Table of Contents

- [🎯 Problem Statement](#-problem-statement)
- [🏗️ Solution Architecture](#️-solution-architecture)
- [💡 Design Patterns Used](#-design-patterns-used)
- [🔧 Implementation Details](#-implementation-details)
- [🚀 Usage Examples](#-usage-examples)
- [📊 Class Relationships](#-class-relationships)
- [🎓 Key Learning Points](#-key-learning-points)
- [❓ Interview Questions](#-interview-questions)

## 🎯 Problem Statement

### Business Context
A software system needs to send notifications through multiple channels (logging, email, SMS, Slack). The requirements are:

1. **Flexible Channel Combination**: Support any combination of notification channels
2. **Runtime Configuration**: Add/remove channels without code changes
3. **Backward Compatibility**: Existing functionality should remain unchanged
4. **Extensibility**: Easy to add new notification channels
5. **Validation**: Each channel should validate its specific requirements
6. **Error Handling**: Graceful degradation when channels fail

### Technical Challenges
- **Tight Coupling**: Direct implementation would create dependencies between channels
- **Code Duplication**: Similar validation and error handling across channels
- **Scalability**: Adding new channels requires modifying existing classes
- **Configuration Complexity**: Managing multiple channel combinations

## 🏗️ Solution Architecture

### Decorator Pattern Structure

```
┌─────────────────────────────────────────────────────────────┐
│                    Notifier Interface                        │
│  + send(message, metadata): NotificationResult             │
│  + getChannels(): String                                    │
│  + canSend(metadata): boolean                              │
└─────────────────────────────────────────────────────────────┘
                            ▲
                            │
              ┌─────────────┴─────────────┐
              │                           │
┌─────────────▼─────────────┐  ┌─────────▼─────────────┐
│      BaseNotifier         │  │  NotifierDecorator    │
│  (Concrete Component)     │  │  (Abstract Decorator) │
│                           │  │  - wrappedNotifier    │
│  + send(): Result         │  │  + send(): Result     │
│  + getChannels(): "LOG"   │  │  # performNotification│
└───────────────────────────┘  └─────────▲─────────────┘
                                         │
                            ┌────────────┼────────────┐
                            │            │            │
                 ┌─────────▼──┐  ┌──────▼───┐  ┌─────▼─────┐
                 │EmailNotifier│  │SMSNotifier│  │SlackNotifier│
                 │            │  │           │  │             │
                 │+ send()    │  │+ send()   │  │+ send()     │
                 │+ validate  │  │+ validate │  │+ validate   │
                 └────────────┘  └───────────┘  └─────────────┘
```

### Component Interactions

```
Client Request
     │
     ▼
SlackNotifier.send()
     │
     ├─── performNotification() [Slack-specific logic]
     │
     ▼
SMSNotifier.send()
     │
     ├─── performNotification() [SMS-specific logic]
     │
     ▼
EmailNotifier.send()
     │
     ├─── performNotification() [Email-specific logic]
     │
     ▼
BaseNotifier.send()
     │
     └─── Log notification [Base functionality]
```

## 💡 Design Patterns Used

### 1. Decorator Pattern (Primary)
**Intent**: Attach additional responsibilities to an object dynamically

**Implementation**:
- **Component**: `Notifier` interface
- **ConcreteComponent**: `BaseNotifier` (logging functionality)
- **Decorator**: `NotifierDecorator` abstract class
- **ConcreteDecorators**: `EmailNotifier`, `SMSNotifier`, `SlackNotifier`

### 2. Builder Pattern (Supporting)
**Intent**: Construct complex objects step by step

**Implementation**: `NotificationMetadata.Builder` for flexible metadata creation

### 3. Factory Method Pattern (Supporting)
**Intent**: Create objects without specifying exact classes

**Implementation**: `NotificationResult.success()`, `NotificationResult.failure()` factory methods

### 4. Template Method Pattern (Supporting)
**Intent**: Define algorithm skeleton with customizable steps

**Implementation**: `NotifierDecorator` defines common flow, concrete decorators implement specific steps

## 🔧 Implementation Details

### Core Interface Design

```java
public interface Notifier {
    NotificationResult send(String message, NotificationMetadata metadata);
    default NotificationResult send(String message) { /* convenience method */ }
    String getChannels();
    boolean canSend(NotificationMetadata metadata);
}
```

**Key Design Decisions**:
- **Rich Return Type**: `NotificationResult` instead of void for comprehensive feedback
- **Metadata Support**: Flexible `NotificationMetadata` for channel-specific data
- **Validation Contract**: `canSend()` method for pre-validation
- **Channel Awareness**: `getChannels()` for debugging and monitoring

### Decorator Base Class

```java
public abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrappedNotifier;
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        // Validate → Perform → Delegate → Return pattern
    }
    
    protected abstract NotificationResult performNotification(...);
    protected abstract boolean canHandleNotification(...);
}
```

**Key Features**:
- **Composition over Inheritance**: Wraps rather than extends
- **Template Method**: Common flow with customizable steps
- **Graceful Degradation**: Falls back to wrapped notifier on failure

### Concrete Decorator Examples

#### EmailNotifier
```java
public class EmailNotifier extends NotifierDecorator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("...");
    
    @Override
    protected NotificationResult performNotification(...) {
        // SMTP simulation with detailed logging
        // Priority-based processing delays
        // Format validation and error handling
    }
}
```

**Features**: Email format validation, SMTP configuration, priority handling

#### SMSNotifier
```java
public class SMSNotifier extends NotifierDecorator {
    @Override
    protected NotificationResult performNotification(...) {
        // Message length truncation (160 char limit)
        // Phone number validation (E.164 format)
        // Provider-specific API simulation
    }
}
```

**Features**: Phone validation, message truncation, delivery status simulation

### Supporting Classes

#### NotificationResult
```java
public class NotificationResult {
    // Builder pattern for flexible construction
    public static NotificationResult success(String id, ...);
    public static NotificationResult failure(String message, ...);
    public static NotificationResult pending(String id, ...);
}
```

#### NotificationMetadata
```java
public class NotificationMetadata {
    // Builder pattern with validation
    public static NotificationMetadata defaultMetadata();
    public static NotificationMetadata forRecipient(String recipient);
    public static NotificationMetadata urgent(String recipient, String subject);
}
```

## 🚀 Usage Examples

### Basic Usage

```java
// Single channel
Notifier emailNotifier = new EmailNotifier(new BaseNotifier());
NotificationResult result = emailNotifier.send("Hello World", 
    NotificationMetadata.forRecipient("user@example.com"));
```

### Multi-Channel Composition

```java
// Multiple channels (Decorator Chain)
Notifier multiChannel = new SlackNotifier(
    new SMSNotifier(
        new EmailNotifier(
            new BaseNotifier("AlertSystem")
        )
    )
);

NotificationMetadata metadata = new NotificationMetadata.Builder()
    .recipient("admin@company.com")
    .priority(NotificationPriority.URGENT)
    .subject("Critical Alert")
    .build();

NotificationResult result = multiChannel.send("Server down!", metadata);
```

### Priority-Based Notifications

```java
NotificationMetadata urgentMeta = NotificationMetadata.urgent(
    "oncall@company.com", 
    "Production Issue"
);

NotificationResult result = notifier.send(
    "Database connection failed - immediate attention required!", 
    urgentMeta
);
```

### Validation and Error Handling

```java
// Test different recipient formats
String[] recipients = {
    "user@example.com",     // Email format
    "+1234567890",          // Phone format  
    "#alerts",              // Slack channel
    "@username"             // Slack user
};

for (String recipient : recipients) {
    NotificationMetadata meta = NotificationMetadata.forRecipient(recipient);
    if (notifier.canSend(meta)) {
        NotificationResult result = notifier.send("Test message", meta);
        System.out.println("Sent via: " + result.getChannel());
    }
}
```

## 📊 Class Relationships

### Inheritance Hierarchy
```
Notifier (Interface)
├── BaseNotifier (ConcreteComponent)
└── NotifierDecorator (AbstractDecorator)
    ├── EmailNotifier (ConcreteDecorator)
    ├── SMSNotifier (ConcreteDecorator)
    └── SlackNotifier (ConcreteDecorator)
```

### Composition Relationships
```
NotificationResult ◊─→ NotificationStatus (enum)
NotificationMetadata ◊─→ NotificationPriority (enum)
NotificationMetadata ◊─→ NotificationChannel (enum)
NotifierDecorator ◊─→ Notifier (wrappedNotifier)
```

### Method Call Flow
```
Client → ConcreteDecorator → AbstractDecorator → ConcreteComponent
       ↓                   ↓                   ↓
   send()              send()              send()
       ↓                   ↓                   ↓
validate()         performNotification()   log()
```

## 🎓 Key Learning Points

### Decorator Pattern Benefits

1. **Runtime Composition**: Add behaviors dynamically without subclassing
2. **Single Responsibility**: Each decorator has one focused responsibility  
3. **Open/Closed Principle**: Open for extension, closed for modification
4. **Flexible Combinations**: Any combination of decorators possible

### Real-World Applications

1. **Notification Systems**: Email + SMS + Push notifications
2. **Web Frameworks**: Request/Response processing pipelines
3. **Stream Processing**: Compression + Encryption + Buffering
4. **UI Components**: Borders + Scrollbars + Shadows
5. **Caching Layers**: Memory + Disk + Network caching

### Design Considerations

#### When to Use Decorator Pattern
✅ **Good Fit**:
- Need to add responsibilities dynamically
- Want to avoid explosion of subclasses
- Behaviors can be combined in various ways
- Need to maintain existing interface

❌ **Poor Fit**:
- Simple static behavior addition
- Performance is critical (composition overhead)
- Decorators depend on specific order
- Need to access specific decorator features

#### Common Pitfalls
1. **Deep Nesting**: Too many decorators can hurt performance
2. **Order Dependency**: Some decorators might depend on specific order
3. **Interface Pollution**: Adding too many methods to base interface
4. **Debugging Complexity**: Stack traces become harder to follow

### Performance Considerations

```java
// Efficient: Single decorator
Notifier notifier = new EmailNotifier(new BaseNotifier());

// Less efficient: Deep nesting
Notifier deepNested = new DecorationA(new DecorationB(new DecorationC(...)));
```

## ❓ Interview Questions

### Conceptual Questions

#### Q1: "Explain the Decorator pattern and how it differs from Inheritance?"

**Answer Structure**:
- **Definition**: Decorator attaches additional responsibilities to objects dynamically
- **Composition vs Inheritance**: Decorator uses composition, inheritance uses "is-a" relationships
- **Flexibility**: Decorators can be combined at runtime, inheritance is static
- **Example**: Show notification system with multiple channels

#### Q2: "When would you choose Decorator over Strategy pattern?"

**Answer Structure**:
- **Decorator**: For adding multiple orthogonal behaviors to objects
- **Strategy**: For selecting one algorithm from a family of algorithms
- **Example**: Notification channels (Decorator) vs Sorting algorithms (Strategy)

#### Q3: "How does Decorator pattern support the Open/Closed Principle?"

**Answer Structure**:
- **Open for Extension**: Can add new decorators without modifying existing code
- **Closed for Modification**: Existing classes remain unchanged
- **Example**: Adding SlackNotifier without changing EmailNotifier or BaseNotifier

### Implementation Questions

#### Q4: "Implement a logging decorator that adds timestamp and log level to any notifier."

```java
public class LoggingDecorator extends NotifierDecorator {
    private final LogLevel logLevel;
    
    public LoggingDecorator(Notifier notifier, LogLevel logLevel) {
        super(notifier);
        this.logLevel = logLevel;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        String timestamp = LocalDateTime.now().toString();
        String logMessage = String.format("[%s] [%s] %s", timestamp, logLevel, message);
        
        // Log the attempt
        System.out.println("LOG: " + logMessage);
        
        // Delegate to wrapped notifier
        return wrappedNotifier.send(message, metadata);
    }
    
    @Override
    protected NotificationResult performNotification(String message, NotificationMetadata metadata) {
        // This decorator doesn't perform its own notification, just logging
        return wrappedNotifier.send(message, metadata);
    }
    
    @Override
    protected boolean canHandleNotification(NotificationMetadata metadata) {
        return true; // Logging can always be done
    }
    
    @Override
    protected String getChannelIdentifier() {
        return "LOGGING";
    }
}
```

#### Q5: "How would you handle the case where decorators need to be applied in a specific order?"

**Answer Structure**:
- **Builder Pattern**: Create a NotifierBuilder that enforces order
- **Chain of Responsibility**: Use explicit ordering mechanism
- **Validation**: Add order validation in decorator constructors
- **Documentation**: Clearly document order dependencies

```java
public class NotifierBuilder {
    private Notifier notifier;
    
    public NotifierBuilder() {
        this.notifier = new BaseNotifier();
    }
    
    public NotifierBuilder withEmail() {
        this.notifier = new EmailNotifier(notifier);
        return this;
    }
    
    public NotifierBuilder withSMS() {
        // SMS should come after email for escalation
        if (!(notifier instanceof EmailNotifier)) {
            throw new IllegalStateException("SMS requires email to be configured first");
        }
        this.notifier = new SMSNotifier(notifier);
        return this;
    }
    
    public Notifier build() {
        return notifier;
    }
}
```

### Design Questions

#### Q6: "Design a notification system that supports rate limiting and retry mechanisms using decorators."

**Answer Structure**:
- **RateLimitingDecorator**: Limits notifications per time window
- **RetryDecorator**: Retries failed notifications with backoff
- **CircuitBreakerDecorator**: Prevents cascade failures
- **Composition**: Show how these can be combined

#### Q7: "How would you implement notification templates and personalization using the decorator pattern?"

**Answer Structure**:
- **TemplateDecorator**: Applies message templates
- **PersonalizationDecorator**: Adds user-specific content
- **LocalizationDecorator**: Translates messages based on user locale
- **Chaining**: Show proper order and dependencies

### Advanced Questions

#### Q8: "Critique this decorator implementation - what are the potential issues?"

```java
public class BadEmailDecorator implements Notifier {
    private Notifier notifier;
    
    public void send(String message) {
        sendEmail(message);
        notifier.send(message);
    }
}
```

**Issues to Identify**:
1. **No error handling**: Email failure doesn't affect flow
2. **Missing validation**: No email format checking
3. **Hard-coded behavior**: No configuration options
4. **Interface violation**: Missing methods from Notifier interface
5. **No composition safety**: notifier could be null

#### Q9: "How would you test a complex decorator chain?"

**Answer Structure**:
- **Unit Testing**: Test each decorator in isolation
- **Integration Testing**: Test decorator combinations
- **Mock Testing**: Mock wrapped notifiers to verify delegation
- **Behavioral Testing**: Verify proper order of operations

```java
@Test
public void testDecoratorChain() {
    // Mock the base notifier
    Notifier mockBase = Mockito.mock(Notifier.class);
    when(mockBase.send(any(), any())).thenReturn(NotificationResult.success(...));
    
    // Create decorator chain
    Notifier chain = new EmailNotifier(new SMSNotifier(mockBase));
    
    // Test behavior
    NotificationResult result = chain.send("test", metadata);
    
    // Verify delegation occurred
    verify(mockBase).send("test", metadata);
    
    // Verify result
    assertTrue(result.isSuccess());
    assertEquals("EMAIL + SMS + LOG", chain.getChannels());
}
```

## 🔗 Related Patterns

### Pattern Combinations

#### Decorator + Observer
```java
public class ObservableDecorator extends NotifierDecorator {
    private List<NotificationObserver> observers = new ArrayList<>();
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        NotificationResult result = super.send(message, metadata);
        notifyObservers(result);
        return result;
    }
}
```

#### Decorator + Factory
```java
public class NotifierFactory {
    public static Notifier createEmailNotifier() {
        return new EmailNotifier(new BaseNotifier());
    }
    
    public static Notifier createMultiChannelNotifier() {
        return new SlackNotifier(new SMSNotifier(new EmailNotifier(new BaseNotifier())));
    }
}
```

#### Decorator + Command
```java
public class NotificationCommand {
    private final Notifier notifier;
    private final String message;
    private final NotificationMetadata metadata;
    
    public NotificationResult execute() {
        return notifier.send(message, metadata);
    }
}
```

---

## 📚 References

- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
- [Decorator Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/decorator)
- [Java Design Pattern Best Practices](https://docs.oracle.com/javase/tutorial/)

---

*This implementation demonstrates professional-grade code with comprehensive error handling, validation, and real-world applicability for technical interviews and production systems.*