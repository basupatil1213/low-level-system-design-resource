# 🏭 Factory Design Pattern

The Factory Design Pattern is a creational pattern that provides an interface for creating objects without specifying their exact classes. It encapsulates object creation logic and promotes loose coupling.

## 🎯 Problem Statement

When you need to create objects but don't want to expose the instantiation logic to the client, and you want to refer to the newly created object through a common interface.

## 💡 Solution

The Factory pattern suggests creating objects through a factory method instead of direct constructor calls. This method can decide which class to instantiate based on input parameters.

## 🏗️ Structure

```
Interface/Abstract Class
    ↑
Concrete Products
    ↑
Factory (creates products)
    ↑
Client (uses factory)
```

## 📁 Implementations

This directory contains two comprehensive Factory pattern implementations:

### 🔔 [Notification System](notificationSystem/)
A communication system supporting multiple notification channels.

**Components:**
- **`Notification`** (Interface) - Contract for all notification types
- **`EmailNotification`** - Email-specific implementation
- **`SMSNotification`** - SMS-specific implementation  
- **`NotificationFactory`** - String-based factory for creating notifications
- **`Client`** - Demonstration of usage patterns

**Key Features:**
- String-based type identification
- Exception handling for unsupported types
- Easy extensibility for new notification channels
- Simple factory method approach

### 💳 [Payment System](PaymentSystem/)
A financial transaction system handling multiple payment methods.

**Components:**
- **`Payment`** (Interface) - Contract for all payment methods
- **`UPIPayment`** - UPI/Digital wallet implementation
- **`PayPalPayment`** - PayPal gateway integration
- **`CreditCardPayment`** - Credit card processing
- **`PaymentFactory`** - Enum-based factory for creating payments
- **`PaymentType`** (Enum) - Type-safe payment method identification
- **`Client`** - Demonstration of payment processing

**Key Features:**
- Enum-based type safety
- Modern Java switch expressions
- Compile-time validation
- Financial domain modeling

## 🔧 Key Features

- **Encapsulation**: Object creation logic is encapsulated
- **Flexibility**: Easy to add new notification types
- **Loose Coupling**: Client doesn't depend on concrete classes
- **Single Responsibility**: Factory handles object creation

## 🚀 Usage Examples

### Notification System
```java
// Create email notification
Notification emailNotification = NotificationFactory.getNotification("email");
emailNotification.send("Welcome to our service!");

// Create SMS notification
Notification smsNotification = NotificationFactory.getNotification("sms");
smsNotification.send("Your OTP is 123456");
```

### Payment System
```java
// Process UPI payment
Payment upiPayment = PaymentFactory.getPaymentMethod(PaymentType.UPI);
upiPayment.pay(299.99);

// Process PayPal payment
Payment paypalPayment = PaymentFactory.getPaymentMethod(PaymentType.PAYPAL);
paypalPayment.pay(149.50);
```

## ✅ Advantages

1. **Loose Coupling**: Client is decoupled from concrete classes
2. **Easy Extension**: New notification types can be added easily
3. **Centralized Creation**: All object creation logic in one place
4. **Code Reusability**: Factory can be reused across different clients
5. **Maintainability**: Changes to creation logic affect only the factory

## ❌ Disadvantages

1. **Complexity**: Adds extra layer of abstraction
2. **Code Overhead**: More classes and interfaces to maintain
3. **Inflexibility**: Hard to change factory behavior at runtime

## 🎯 When to Use

- When you have a family of related products
- When object creation is complex
- When you want to hide creation logic from clients
- When you need to support multiple product variants
- When you want to achieve loose coupling

## 🔄 Variations

1. **Simple Factory**: Static method that creates objects
2. **Factory Method**: Subclasses decide which class to instantiate
3. **Abstract Factory**: Creates families of related objects

## 🧪 Running the Examples

### Notification System
```bash
# Compile and run the notification system
javac notificationSystem/*.java
java notificationSystem.Client
```

Expected Output:
```
Email Notification: User created successfully!
SMS Notification: User created successfully!
Notification type not supported: push
```

### Payment System
```bash
# Compile and run the payment system
javac PaymentSystem/*.java
java PaymentSystem.Client
```

Expected Output:
```
Paid 143.0 via PayPal
Paid 286.0 via UPI
Paid 143.0 via credit card
Unsupported payment type
```

## 🎓 Interview Questions

1. **Q**: What is the Factory Design Pattern?
   **A**: A creational pattern that creates objects without exposing instantiation logic to clients.

2. **Q**: When would you use the Factory pattern?
   **A**: When you need to create objects but want to decouple creation logic from client code.

3. **Q**: What's the difference between Factory and Abstract Factory?
   **A**: Factory creates one type of object, Abstract Factory creates families of related objects.

4. **Q**: How does Factory pattern promote loose coupling?
   **A**: Clients depend on interfaces/abstract classes rather than concrete implementations.

5. **Q**: Compare string-based vs enum-based factories
   **A**: Enum-based provides compile-time type safety, prevents typos, and offers better IDE support compared to string-based approaches.

6. **Q**: How would you extend the payment system for international markets?
   **A**: Use Abstract Factory pattern to create region-specific payment ecosystems with localized payment methods.

## 🔍 Pattern Comparison

| Aspect | Notification System | Payment System |
|--------|-------------------|----------------|
| Type Safety | String-based (runtime) | Enum-based (compile-time) |
| Extensibility | Manual string handling | Automatic enum validation |
| Error Handling | Runtime exceptions | Compile-time checks |
| IDE Support | Limited | Full auto-completion |
| Maintenance | Prone to typos | Type-safe refactoring |
| Use Case | Simple notifications | Financial transactions |

## 🔗 Related Patterns

- **Abstract Factory**: Creates families of objects
- **Builder**: Constructs complex objects step by step
- **Prototype**: Creates objects by cloning existing instances
- **Singleton**: Ensures only one instance exists