# Builder Pattern Implementation

## Overview

The Builder Pattern is a creational design pattern that provides a flexible solution for constructing complex objects step by step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

## When to Use Builder Pattern

- **Complex Object Construction**: When objects have many parameters or construction steps
- **Optional Parameters**: When objects have many optional parameters
- **Immutable Objects**: When you need to create immutable objects safely
- **Step-by-Step Construction**: When object creation involves multiple steps
- **Different Representations**: When the same construction process can create different object representations

## Key Components

### 1. Product
The complex object being constructed.

### 2. Builder (Interface/Abstract Class)
Defines the interface for constructing parts of the Product.

### 3. Concrete Builder
Implements the Builder interface and provides specific implementations for constructing parts.

### 4. Director (Optional)
Constructs objects using the Builder interface, encapsulating the construction logic.

## Implementation Examples

### [Meal Ordering System](mealOrderingSystem/)
A comprehensive implementation demonstrating professional Builder Pattern usage in a restaurant ordering context.

**Features:**
- Fluent interface for method chaining
- Multiple builder types (Kids, Deluxe meals)
- Director pattern integration
- Validation and error handling
- Immutable product objects
- Comprehensive nutritional analysis

**Key Classes:**
- `Meal` - Product class (immutable meal order)
- `MealBuilder` - Builder interface
- `AbstractMealBuilder` - Common builder functionality
- `KidsMealBuilder` - Child-friendly meal construction
- `DeluxeMealBuilder` - Premium meal construction
- `MealDirector` - Standardized construction processes

**Usage Example:**
```java
// Using static factory method
MealBuilder builder = Meal.builder(MealType.DELUXE);
Meal meal = builder
    .reset("ORD-001", "John Doe")
    .buildMain()
    .buildSide()
    .buildDrink()
    .buildDessert()
    .addSpecialInstruction("No onions")
    .build();

// Using director
MealDirector director = new MealDirector();
Meal standardMeal = director.constructMeal("ORD-002", "Jane Smith", MealType.KIDS);
```

## Design Patterns Integration

### Builder + Director Pattern
The Director pattern works alongside the Builder pattern to:
- Encapsulate construction algorithms
- Provide standardized construction processes
- Hide complex construction logic from clients
- Enable different construction strategies

### Builder + Abstract Factory
Can be combined with Abstract Factory when:
- Different families of builders are needed
- Builder creation needs to be abstracted
- Multiple product hierarchies exist

## Best Practices

### 1. Immutable Products
```java
public class Product {
    private final String field1;
    private final String field2;
    
    // Package-private constructor
    Product(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }
    
    // Static factory method
    public static Builder builder() {
        return new ConcreteBuilder();
    }
}
```

### 2. Fluent Interface
```java
public interface Builder {
    Builder setField1(String value);
    Builder setField2(String value);
    Product build();
}
```

### 3. Validation
```java
public Product build() {
    validate();
    return new Product(field1, field2);
}

private void validate() {
    if (field1 == null) {
        throw new IllegalStateException("Field1 is required");
    }
}
```

### 4. Method Chaining
```java
Product product = Product.builder()
    .setField1("value1")
    .setField2("value2")
    .build();
```

## Common Variations

### 1. Telescoping Constructor Problem Solution
Instead of multiple constructors, use builder:
```java
// Bad: Telescoping constructors
public Product(String a) { ... }
public Product(String a, String b) { ... }
public Product(String a, String b, String c) { ... }

// Good: Builder pattern
Product product = Product.builder()
    .setA("valueA")
    .setB("valueB")
    .setC("valueC")
    .build();
```

### 2. Step Builder
Enforces construction order:
```java
public interface FirstStep {
    SecondStep setRequired(String value);
}

public interface SecondStep {
    FinalStep setOptional(String value);
    Product build();
}
```

### 3. Generic Builder
Reusable builder for similar objects:
```java
public class GenericBuilder<T> {
    private final Class<T> clazz;
    private final Map<String, Object> properties = new HashMap<>();
    
    public GenericBuilder(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public GenericBuilder<T> set(String property, Object value) {
        properties.put(property, value);
        return this;
    }
    
    public T build() {
        // Use reflection to create instance
        return createInstance();
    }
}
```

## Advantages

1. **Flexibility**: Easy to add new construction steps or modify existing ones
2. **Readability**: Fluent interface makes code more readable
3. **Immutability**: Can create immutable objects safely
4. **Validation**: Can validate object state before creation
5. **Optional Parameters**: Handle many optional parameters elegantly
6. **Different Representations**: Same process can create different objects

## Disadvantages

1. **Complexity**: Adds additional classes and complexity
2. **Performance**: Slight overhead due to additional method calls
3. **Memory**: Uses more memory during construction
4. **Learning Curve**: Requires understanding of the pattern

## Real-World Applications

1. **StringBuilder in Java**: Building strings incrementally
2. **HTTP Client Builders**: Configuring HTTP requests
3. **GUI Framework Builders**: Creating complex UI components
4. **Configuration Objects**: Building complex configurations
5. **Test Data Builders**: Creating test objects with varying parameters

## Testing Builder Pattern

```java
@Test
public void testMealBuilder() {
    // Arrange
    MealBuilder builder = Meal.builder(MealType.KIDS);
    
    // Act
    Meal meal = builder
        .reset("TEST-001", "Test Customer")
        .buildMain()
        .buildSide()
        .buildDrink()
        .build();
    
    // Assert
    assertNotNull(meal);
    assertEquals("TEST-001", meal.getOrderId());
    assertTrue(meal.hasItem(ItemCategory.MAIN_ITEM));
    assertTrue(meal.hasItem(ItemCategory.SIDE));
    assertTrue(meal.hasItem(ItemCategory.DRINK));
}

@Test
public void testBuilderValidation() {
    MealBuilder builder = Meal.builder(MealType.KIDS);
    
    // Should throw exception for incomplete meal
    assertThrows(IllegalStateException.class, () -> {
        builder.build(); // No reset called
    });
}
```

## Comparison with Other Patterns

| Pattern | Purpose | When to Use |
|---------|---------|-------------|
| **Builder** | Construct complex objects step by step | Many parameters, optional fields, immutable objects |
| **Factory Method** | Create objects without specifying exact classes | Need to delegate object creation to subclasses |
| **Abstract Factory** | Create families of related objects | Need to create families of related products |
| **Prototype** | Create objects by cloning existing instances | Object creation is expensive, need copies |

## Learning Path

1. **Start Here**: [Meal Ordering System](mealOrderingSystem/) - Comprehensive implementation
2. **Practice**: Implement builders for your own domain objects
3. **Explore**: Try different variations (Step Builder, Generic Builder)
4. **Integrate**: Combine with other patterns (Director, Abstract Factory)

## References

- **Design Patterns**: Elements of Reusable Object-Oriented Software (Gang of Four)
- **Effective Java**: Joshua Bloch (Item 2: Consider a builder when faced with many constructor parameters)
- **Clean Code**: Robert C. Martin

---

**Note**: Each implementation includes comprehensive examples, testing scenarios, and best practices for professional development.

**Author**: Basavaraj Patil  
**Last Updated**: August 2, 2025
