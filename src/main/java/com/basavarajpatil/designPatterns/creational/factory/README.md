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

## 📁 Implementation: Notification System

### Components

1. **`Notification`** (Interface)
   - Defines the contract for all notification types
   - Method: `send(String message)`

2. **`EmailNotification`** (Concrete Product)
   - Implements email notification functionality
   - Sends messages via email

3. **`SMSNotification`** (Concrete Product)
   - Implements SMS notification functionality
   - Sends messages via SMS

4. **`NotificationFactory`** (Factory)
   - Creates notification objects based on type
   - Method: `getNotification(String type)`

5. **`Client`** (Consumer)
   - Uses the factory to create notifications
   - Demonstrates pattern usage

## 🔧 Key Features

- **Encapsulation**: Object creation logic is encapsulated
- **Flexibility**: Easy to add new notification types
- **Loose Coupling**: Client doesn't depend on concrete classes
- **Single Responsibility**: Factory handles object creation

## 🚀 Usage Example

```java
// Create email notification
Notification emailNotification = NotificationFactory.getNotification("email");
emailNotification.send("Welcome to our service!");

// Create SMS notification
Notification smsNotification = NotificationFactory.getNotification("sms");
smsNotification.send("Your OTP is 123456");
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

## 🧪 Running the Example

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

## 🎓 Interview Questions

1. **Q**: What is the Factory Design Pattern?
   **A**: A creational pattern that creates objects without exposing instantiation logic to clients.

2. **Q**: When would you use the Factory pattern?
   **A**: When you need to create objects but want to decouple creation logic from client code.

3. **Q**: What's the difference between Factory and Abstract Factory?
   **A**: Factory creates one type of object, Abstract Factory creates families of related objects.

4. **Q**: How does Factory pattern promote loose coupling?
   **A**: Clients depend on interfaces/abstract classes rather than concrete implementations.

## 🔗 Related Patterns

- **Abstract Factory**: Creates families of objects
- **Builder**: Constructs complex objects step by step
- **Prototype**: Creates objects by cloning existing instances
- **Singleton**: Ensures only one instance exists