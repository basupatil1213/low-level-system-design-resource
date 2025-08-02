# Meal Ordering System - Builder Pattern Implementation

## Overview

This project demonstrates a professional implementation of the **Builder Pattern** through a comprehensive meal ordering system. The system allows customers to construct complex meal orders with various components (main items, sides, drinks, desserts) while maintaining flexibility and ensuring proper object construction.

## Design Pattern: Builder Pattern

The Builder Pattern is a creational design pattern that provides a flexible solution for constructing complex objects step by step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

### Key Benefits
- **Flexible Object Construction**: Create objects with different configurations
- **Method Chaining**: Fluent interface for readable code
- **Validation**: Ensure objects are properly constructed before use
- **Immutability**: Create immutable objects safely
- **Director Pattern Integration**: Standardized construction processes

## Architecture

### Core Components

#### 1. Product (`Meal`)
```java
public class Meal {
    // Immutable meal order with package-private constructor
    // Only builders can create instances
}
```

#### 2. Builder Interface (`MealBuilder`)
```java
public interface MealBuilder {
    MealBuilder reset(String orderId, String customerName);
    MealBuilder buildMain();
    MealBuilder buildSide();
    MealBuilder buildDrink();
    MealBuilder buildDessert();
    MealBuilder addSpecialInstruction(String instruction);
    Meal build();
}
```

#### 3. Abstract Builder (`AbstractMealBuilder`)
```java
public abstract class AbstractMealBuilder implements MealBuilder {
    // Common implementation with template methods
    // Concrete builders override selection methods
}
```

#### 4. Concrete Builders
- **`KidsMealBuilder`**: Child-friendly meal construction
- **`DeluxeMealBuilder`**: Premium meal construction with gourmet options

#### 5. Director (`MealDirector`)
```java
public class MealDirector {
    // Standardized construction algorithms
    // Encapsulates complex building logic
}
```

## Class Diagram

```
┌─────────────────┐    ┌──────────────────┐
│   MealDirector  │────│   MealBuilder    │
└─────────────────┘    └──────────────────┘
                                △
                                │
                   ┌────────────────────────┐
                   │  AbstractMealBuilder   │
                   └────────────────────────┘
                                △
                    ┌───────────┴───────────┐
                    │                       │
         ┌──────────────────┐    ┌──────────────────┐
         │  KidsMealBuilder │    │ DeluxeMealBuilder│
         └──────────────────┘    └──────────────────┘
                    │                       │
                    └───────────┬───────────┘
                                │
                         ┌─────────────┐
                         │    Meal     │
                         └─────────────┘
```

## Features

### 🎯 Builder Pattern Implementation
- **Fluent Interface**: Method chaining for readable construction
- **Step-by-Step Construction**: Build meals component by component
- **Validation**: Ensure complete and valid meal orders
- **Immutable Products**: Thread-safe meal objects

### 🍽️ Meal Components
- **Main Items**: Burgers, sandwiches, steaks, and more
- **Side Items**: Fries, salads, gourmet options
- **Drinks**: Beverages from water to premium wines
- **Desserts**: Sweet treats and gourmet desserts

### 👨‍👩‍👧‍👦 Specialized Builders
- **Kids Meals**: Age-appropriate portions and options
- **Deluxe Meals**: Premium ingredients and gourmet selections

### 📊 Advanced Features
- **Nutritional Analysis**: Calorie counting and health metrics
- **Dietary Preferences**: Vegetarian detection
- **Price Calculation**: Automatic total computation
- **Order Receipts**: Professional formatted receipts

## Quick Start

### Basic Usage
```java
// Using static factory method
MealBuilder builder = Meal.builder(MealType.KIDS);
Meal meal = builder
    .reset("ORD-001", "Alice Johnson")
    .buildMain()
    .buildSide()
    .buildDrink()
    .buildDessert()
    .addSpecialInstruction("No onions please")
    .build();

meal.showItems();
```

### Director Pattern Usage
```java
MealDirector director = new MealDirector();
Meal standardMeal = director.constructMeal("ORD-002", "Bob Smith", MealType.DELUXE);
```

### Advanced Construction
```java
MealBuilder builder = new DeluxeMealBuilder();
Meal premiumMeal = builder
    .reset("ORD-003", "Carol Williams")
    .buildMain()      // Premium steak
    .buildSide()      // Gourmet mac and cheese
    .buildDrink()     // Premium wine
    .buildDessert()   // Gourmet cheesecake
    .addSpecialInstruction("Medium rare steak")
    .addSpecialInstruction("Extra cheese")
    .build();

System.out.println("Total: $" + premiumMeal.getTotalPrice());
System.out.println("Calories: " + premiumMeal.getTotalCalories());
System.out.println("Vegetarian: " + premiumMeal.isVegetarian());
System.out.println("Complete: " + premiumMeal.isComplete());
```

## Running the Application

### Compile and Run
```bash
# Compile the project
mvn clean compile

# Run the demonstration
mvn exec:java -Dexec.mainClass="com.basavarajpatil.designPatterns.creational.builder.mealOrderingSystem.Client"
```

### Expected Output
```
═══════════════════════════════════════════════
           KIDS MEAL ORDER RECEIPT
═══════════════════════════════════════════════
Order ID: ORD-001
Customer: Alice Johnson
Order Time: 2025-08-02 10:30:15
───────────────────────────────────────────────
Main Item   : Chicken Sandwich - $9.99 (520 cal)
Side        : French Fries - $3.99 (240 cal)
Drink       : Orange Juice - $3.49 (120 cal)
Dessert     : Cookies - $2.99 (160 cal)
───────────────────────────────────────────────
Total Items: 4
Total Calories: 1040 cal
TOTAL PRICE: $20.46
═══════════════════════════════════════════════
```

## Implementation Details

### Pattern Benefits Demonstrated

1. **Encapsulation of Construction Logic**
   - Complex meal assembly is hidden from clients
   - Different builders create different meal types
   - Construction steps are standardized

2. **Flexibility and Extensibility**
   - Easy to add new meal types (new builders)
   - Modify construction process without affecting clients
   - Support for different construction algorithms

3. **Validation and Error Handling**
   - Builders validate construction steps
   - Ensure meals are complete before delivery
   - Graceful handling of invalid configurations

4. **Immutability and Thread Safety**
   - Meal objects are immutable once built
   - Thread-safe construction process
   - No external modification after creation

### Design Principles Applied

- **Single Responsibility**: Each builder handles one meal type
- **Open/Closed**: Open for extension (new builders), closed for modification
- **Dependency Inversion**: Clients depend on abstractions, not concretions
- **Interface Segregation**: Clean, focused interfaces for building

## File Structure

```
mealOrderingSystem/
├── README.md                     # This file
├── Client.java                   # Demonstration client
├── Meal.java                     # Product class
├── MealBuilder.java              # Builder interface
├── AbstractMealBuilder.java      # Abstract builder implementation
├── KidsMealBuilder.java          # Kids meal builder
├── DeluxeMealBuilder.java        # Deluxe meal builder
├── MealDirector.java             # Director for standardized construction
├── MealType.java                 # Meal type enumeration
├── MealItem.java                 # Individual meal item
├── MealItemType.java             # Available meal items
└── ItemCategory.java             # Item categorization
```

## Testing Scenarios

The `Client` class demonstrates various usage scenarios:

1. **Director Pattern Usage**: Standardized meal construction
2. **Fluent Builder Interface**: Method chaining examples
3. **Static Factory Methods**: Convenient builder creation
4. **Complex Meal Building**: Multi-step construction with validation
5. **Error Handling**: Validation and error scenarios

## Best Practices Demonstrated

1. **Builder Pattern Best Practices**
   - Package-private product constructor
   - Static factory methods for builder creation
   - Fluent interface for method chaining
   - Validation before object creation

2. **Java Best Practices**
   - Immutable objects with defensive copying
   - Null safety and validation
   - Proper exception handling
   - Clean code principles

3. **Object-Oriented Design**
   - Composition over inheritance
   - Encapsulation of complex logic
   - Separation of concerns
   - Polymorphism through interfaces

## Extension Points

### Adding New Meal Types
```java
// 1. Add to MealType enum
public enum MealType {
    KIDS, DELUXE, VEGETARIAN  // New type
}

// 2. Create new builder
public class VegetarianMealBuilder extends AbstractMealBuilder {
    // Implement vegetarian-specific selections
}

// 3. Update factory method in Meal class
public static MealBuilder builder(MealType mealType) {
    switch (mealType) {
        case KIDS: return new KidsMealBuilder();
        case DELUXE: return new DeluxeMealBuilder();
        case VEGETARIAN: return new VegetarianMealBuilder();  // New
        default: throw new IllegalArgumentException("Unsupported meal type: " + mealType);
    }
}
```

### Adding New Menu Items
```java
// Add to MealItemType enum
QUINOA_SALAD("Quinoa Power Salad", ItemCategory.MAIN_ITEM, 12.99, 350),
KOMBUCHA("Organic Kombucha", ItemCategory.DRINK, 4.99, 30);
```

## Common Use Cases

1. **Restaurant Ordering Systems**: Flexible meal customization
2. **Catering Services**: Standardized meal preparation
3. **Food Delivery Apps**: Complex order construction
4. **Nutritional Planning**: Calorie and dietary tracking

## Performance Considerations

- **Memory Efficiency**: Builders reuse common components
- **Construction Speed**: Optimized for frequent meal creation
- **Thread Safety**: Immutable products, stateless builders
- **Scalability**: Easy to add new builders and meal types

## Learning Outcomes

After studying this implementation, you will understand:

1. **Builder Pattern Implementation**: Complete pattern structure
2. **Director Pattern Integration**: Standardized construction processes
3. **Fluent Interface Design**: Method chaining techniques
4. **Immutable Object Creation**: Safe object construction
5. **Validation Strategies**: Ensuring object integrity
6. **Extensible Design**: Adding new functionality cleanly

## References

- **Design Patterns**: Elements of Reusable Object-Oriented Software (Gang of Four)
- **Effective Java**: Joshua Bloch (Builder Pattern best practices)
- **Clean Code**: Robert C. Martin (Code quality principles)

---

**Author**: Basavaraj Patil  
**Version**: 1.0  
**Last Updated**: August 2, 2025

This implementation demonstrates enterprise-level Builder Pattern usage with real-world complexity and professional code quality standards.
