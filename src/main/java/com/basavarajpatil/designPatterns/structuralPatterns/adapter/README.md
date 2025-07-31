# 🔌 Adapter Design Pattern

The Adapter Design Pattern allows incompatible interfaces to work together by acting as a bridge between them. It's a structural pattern that converts the interface of a class into another interface that clients expect, enabling classes to work together that couldn't otherwise due to incompatible interfaces.

## 🎯 Problem Statement

When integrating external libraries, third-party APIs, or legacy systems, you often encounter interface incompatibilities:

- **Different Method Names**: External API uses `chargePayment()` while your system expects `processPayment()`
- **Parameter Mismatches**: External service expects cents, your system uses dollars
- **Response Format Differences**: External API returns XML, your system expects objects
- **Protocol Differences**: External service uses different communication protocols

## 💡 Solution Approach

The Adapter pattern provides:
- **Interface Translation**: Converts incompatible interfaces to compatible ones
- **Legacy Integration**: Enables modern systems to work with legacy components
- **Third-party Integration**: Seamlessly integrates external libraries and services
- **Minimal Code Changes**: Adapts external interfaces without modifying existing client code

## 🏗️ Pattern Structure

```
Client → Target Interface → Adapter → Adaptee (External Interface)
                             ↑              ↓
                       Implements      Delegates to
                    Target Interface  External System
```

## 📁 Implementations

This directory contains comprehensive implementations of the Adapter pattern:

### 💳 Payment Gateway Integration (`paymentGateway/`)
E-commerce payment processing system that integrates multiple payment providers with incompatible APIs.

**Key Features**:
- **Multiple Providers**: Stripe, PayPal, Square integration
- **API Translation**: Handles different method signatures and parameter formats
- **Unit Conversion**: Dollars ↔ Cents conversion for different providers
- **Response Normalization**: Converts various response formats to standard format
- **Error Handling**: Comprehensive error handling across different provider APIs

**Adaptees Demonstrated**:
- **PayPal SDK**: Two-step payment process with Map-based parameters
- **Square API**: Cents-based amounts with location and order requirements
- **Stripe**: Direct implementation (no adapter needed)

**Adaptation Challenges Solved**:
- Parameter format conversion (Map vs direct parameters)
- Unit conversions (dollars vs cents)
- Method name translation (`chargePayment` vs `processPayment`)
- Response object normalization
- Different validation requirements

## 🔧 When to Use Adapter Pattern

### Perfect Scenarios
- **Third-party Integration**: Integrating external libraries with incompatible interfaces
- **Legacy System Integration**: Making old systems work with new architectures
- **API Version Migration**: Adapting old API versions to new interface standards
- **Cross-platform Development**: Adapting platform-specific APIs to common interfaces

### Anti-patterns (When NOT to Use)
- **Simple Interface Changes**: Use refactoring instead of adapters for minor changes
- **Over-engineering**: Don't create adapters for interfaces you control
- **Performance Critical Code**: Adapters add an extra layer of indirection

## ✅ Pattern Benefits

### Integration Benefits
- **Non-intrusive**: Doesn't require modifying existing code
- **Reusability**: Existing client code works with adapted interfaces
- **Flexibility**: Easy to switch between different implementations
- **Maintainability**: Encapsulates adaptation logic in dedicated classes

### Design Benefits
- **Single Responsibility**: Each adapter handles one specific interface translation
- **Open/Closed Principle**: Add new providers without modifying existing code
- **Dependency Inversion**: Client depends on abstractions, not concrete implementations
- **Interface Segregation**: Clean separation between client and external interfaces

## 🧪 Common Use Cases

### Software Integration
```java
// Database adapter for different database systems
public class MySQLAdapter implements DatabaseInterface {
    private MySQLConnector mysqlConnector;
    
    @Override
    public QueryResult executeQuery(String query) {
        MySQLResult result = mysqlConnector.runQuery(query);
        return convertToStandardResult(result);
    }
}
```

### UI Framework Integration
```java
// Adapt different UI libraries to common interface
public class SwingButtonAdapter implements UIButton {
    private JButton swingButton;
    
    @Override
    public void onClick(ClickHandler handler) {
        swingButton.addActionListener(e -> handler.handle());
    }
}
```

### File Format Conversion
```java
// Adapt different file parsers to common interface
public class XMLParserAdapter implements DocumentParser {
    private XMLReader xmlReader;
    
    @Override
    public Document parse(String content) {
        XMLDocument xmlDoc = xmlReader.parse(content);
        return convertToStandardDocument(xmlDoc);
    }
}
```

## 🔄 Related Patterns

### Complementary Patterns
- **Bridge**: Similar structure but different intent (abstraction vs adaptation)
- **Facade**: Simplifies interface (vs Adapter which translates interface)
- **Decorator**: Adds behavior (vs Adapter which translates interface)
- **Proxy**: Controls access (vs Adapter which translates interface)

### Pattern Combinations
- **Adapter + Factory**: Factory creates appropriate adapters based on configuration
- **Adapter + Strategy**: Different adaptation strategies for different scenarios
- **Adapter + Observer**: Adapting event notification systems

## 📊 Pattern Comparison

| Aspect | Adapter | Bridge | Facade | Proxy |
|--------|---------|---------|---------|-------|
| Intent | Interface compatibility | Abstraction-implementation separation | Simplification | Access control |
| Structure | Wraps incompatible interface | Separate abstraction hierarchy | Unified interface to subsystem | Same interface as subject |
| Usage | Legacy integration | Platform independence | Complex system simplification | Lazy loading, caching |
| Coupling | Tight to adaptee | Loose coupling | Loose to subsystem | Tight to subject |

## 🎓 Interview Questions

### Conceptual Questions
**Q**: Explain the difference between Adapter and Facade patterns.  
**A**: Adapter makes incompatible interfaces compatible (interface translation), while Facade provides a simplified interface to a complex subsystem (interface simplification).

**Q**: When would you choose composition over inheritance for Adapter?  
**A**: Use composition (Object Adapter) when you can't modify the adaptee class, need to adapt multiple classes, or want loose coupling. Use inheritance (Class Adapter) only when you need to override adaptee methods.

### Implementation Questions
**Q**: How would you handle multiple incompatible interfaces that need adaptation?  
**A**: Create separate adapters for each interface, use a common adapter factory, or implement multi-interface adapters that handle multiple adaptees.

**Q**: How do you handle error conditions in adapters?  
**A**: Translate external errors to standard exceptions, provide meaningful error messages, implement retry logic, and ensure consistent error handling across all adapters.

## 🔗 Real-World Examples

### Industry Applications
- **Payment Processing**: Stripe, PayPal, Square API integration
- **Database Systems**: JDBC drivers for different databases
- **Cloud Services**: AWS, Azure, GCP service integration
- **Social Media**: Facebook, Twitter, LinkedIn API integration
- **E-commerce**: Amazon, eBay, Shopify marketplace integration

### Framework Examples
- **Spring Framework**: Data source adapters, message queue adapters
- **Android**: View adapters for RecyclerView, ListView
- **Java**: Collections framework adapters (Arrays.asList)

---

💡 **Key Insight**: The Adapter pattern is essential for modern software integration, enabling seamless communication between incompatible systems while maintaining clean, maintainable code architecture.
