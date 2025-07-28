# 💳 Payment System - Factory Pattern

A comprehensive implementation of the Factory Design Pattern for handling multiple payment methods in an e-commerce or financial application. This system demonstrates how to manage different payment gateways while maintaining loose coupling and extensibility.

## 🎯 Problem Statement

Modern applications need to support multiple payment methods (credit cards, digital wallets, UPI, bank transfers, etc.) without tightly coupling the business logic to specific payment implementations. The challenges include:

- Supporting diverse payment methods with different APIs
- Adding new payment providers without modifying existing code
- Maintaining consistent payment processing interface
- Handling payment method-specific validation and processing
- Ensuring secure and reliable payment transactions

## 💡 Solution Approach

The Factory pattern provides a centralized way to create payment objects based on payment type, promoting:
- **Loose coupling** between business logic and payment implementations
- **Easy extensibility** for adding new payment methods
- **Consistent interface** across all payment types
- **Type safety** using enums instead of strings

## 🏗️ Architecture Overview

```
Client → PaymentFactory → Payment Interface
             ↓                    ↑
        PaymentType Enum    ┌─────┼─────┬─────┐
                           │     │     │     │
                    UPIPayment  │  PayPalPayment
                               │              │
                      CreditCardPayment  DebitCardPayment
                                             (future)
```

## 📁 Implementation Components

### 1. Payment Interface
```java
public interface Payment {
    void pay(double amount);
}
```
**Responsibility**: Defines the contract for all payment methods with a unified payment operation

### 2. PaymentType Enum
```java
public enum PaymentType {
    PAYPAL, UPI, CREDITCARD, DEBITCARD
}
```
**Responsibility**: 
- Type-safe payment method identification
- Prevents invalid payment type errors at compile time
- Extensible for adding new payment types

### 3. Concrete Payment Implementations

#### UPIPayment
```java
public class UPIPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }
}
```
**Responsibility**: Handles UPI-specific payment processing (GPay, PhonePe, Paytm, etc.)

#### PayPalPayment
```java
public class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}
```
**Responsibility**: Integrates with PayPal's payment gateway

#### CreditCardPayment
```java
public class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via credit card");
    }
}
```
**Responsibility**: Processes credit card transactions through card networks

### 4. PaymentFactory
```java
public class PaymentFactory {
    public static Payment getPaymentMethod(PaymentType paymentType) {
        switch (paymentType) {
            case UPI -> new UPIPayment();
            case PAYPAL -> new PayPalPayment();
            case CREDITCARD -> new CreditCardPayment();
            default -> throw new IllegalArgumentException("Unsupported payment type");
        }
    }
}
```
**Responsibility**: Creates appropriate payment objects based on PaymentType enum

## 🔧 Key Design Improvements

### Enum-Based Type Safety
- **Type Safety**: Compile-time validation of payment types
- **IDE Support**: Auto-completion and refactoring support
- **Maintainability**: Centralized payment type definitions
- **Error Prevention**: Impossible to pass invalid payment types

### Modern Java Switch Expression
- **Concise Syntax**: Uses modern switch expressions (Java 14+)
- **Pattern Matching**: Clean and readable code structure
- **Exhaustiveness**: Compiler ensures all cases are handled
- **Performance**: Optimized bytecode generation

### Error Handling Strategy
- **Fail Fast**: Immediate exception for unsupported types
- **Descriptive Messages**: Clear error indication
- **Extensible**: Easy to add new payment types to switch statement

## 🧪 Usage Examples

### Basic Payment Processing
```java
// Process UPI payment
Payment upiPayment = PaymentFactory.getPaymentMethod(PaymentType.UPI);
upiPayment.pay(299.99);

// Process PayPal payment
Payment paypalPayment = PaymentFactory.getPaymentMethod(PaymentType.PAYPAL);
paypalPayment.pay(149.50);

// Process Credit Card payment
Payment cardPayment = PaymentFactory.getPaymentMethod(PaymentType.CREDITCARD);
cardPayment.pay(599.00);
```

### E-commerce Integration
```java
public class OrderService {
    public boolean processOrder(Order order, PaymentType paymentType) {
        try {
            Payment paymentMethod = PaymentFactory.getPaymentMethod(paymentType);
            paymentMethod.pay(order.getTotalAmount());
            
            // Update order status
            order.setStatus(OrderStatus.PAID);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed: " + e.getMessage());
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            return false;
        }
    }
}
```

### Payment Method Selection
```java
public class PaymentProcessor {
    public void processPayment(double amount, String userPreference) {
        PaymentType selectedType = determinePaymentType(userPreference);
        
        Payment paymentMethod = PaymentFactory.getPaymentMethod(selectedType);
        paymentMethod.pay(amount);
    }
    
    private PaymentType determinePaymentType(String preference) {
        return switch (preference.toLowerCase()) {
            case "upi", "gpay", "phonepe" -> PaymentType.UPI;
            case "paypal" -> PaymentType.PAYPAL;
            case "card", "credit" -> PaymentType.CREDITCARD;
            default -> throw new IllegalArgumentException("Unknown payment preference: " + preference);
        };
    }
}
```

## 🔄 Extending the System

### Adding Debit Card Support
```java
// 1. Update PaymentType enum (already exists)
public enum PaymentType {
    PAYPAL, UPI, CREDITCARD, DEBITCARD  // DEBITCARD already defined
}

// 2. Create DebitCardPayment implementation
public class DebitCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via debit card");
    }
}

// 3. Update PaymentFactory
public static Payment getPaymentMethod(PaymentType paymentType) {
    switch (paymentType) {
        case UPI -> new UPIPayment();
        case PAYPAL -> new PayPalPayment();
        case CREDITCARD -> new CreditCardPayment();
        case DEBITCARD -> new DebitCardPayment();  // Add this case
        default -> throw new IllegalArgumentException("Unsupported payment type");
    }
}
```

### Adding Cryptocurrency Support
```java
// 1. Extend PaymentType enum
public enum PaymentType {
    PAYPAL, UPI, CREDITCARD, DEBITCARD, BITCOIN, ETHEREUM
}

// 2. Create crypto payment implementations
public class BitcoinPayment implements Payment {
    @Override
    public void pay(double amount) {
        double btcAmount = convertToBTC(amount);
        System.out.println("Paid " + btcAmount + " BTC (equivalent to $" + amount + ")");
    }
    
    private double convertToBTC(double usdAmount) {
        // Conversion logic here
        return usdAmount / 45000; // Simplified conversion
    }
}
```

## 🏭 Advanced Factory Patterns

### Abstract Factory for Payment Ecosystems
```java
public interface PaymentEcosystemFactory {
    Payment createWalletPayment();
    Payment createCardPayment();
    Payment createBankTransfer();
}

public class IndianPaymentEcosystem implements PaymentEcosystemFactory {
    public Payment createWalletPayment() { return new UPIPayment(); }
    public Payment createCardPayment() { return new RuPayCardPayment(); }
    public Payment createBankTransfer() { return new IMPSPayment(); }
}

public class USPaymentEcosystem implements PaymentEcosystemFactory {
    public Payment createWalletPayment() { return new PayPalPayment(); }
    public Payment createCardPayment() { return new CreditCardPayment(); }
    public Payment createBankTransfer() { return new ACHPayment(); }
}
```

### Factory with Configuration
```java
public class ConfigurablePaymentFactory {
    private static final Map<PaymentType, Supplier<Payment>> paymentCreators = new HashMap<>();
    
    static {
        paymentCreators.put(PaymentType.UPI, UPIPayment::new);
        paymentCreators.put(PaymentType.PAYPAL, PayPalPayment::new);
        paymentCreators.put(PaymentType.CREDITCARD, CreditCardPayment::new);
    }
    
    public static Payment getPaymentMethod(PaymentType type) {
        Supplier<Payment> creator = paymentCreators.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
        return creator.get();
    }
    
    public static void registerPaymentType(PaymentType type, Supplier<Payment> creator) {
        paymentCreators.put(type, creator);
    }
}
```

## ✅ Advantages Demonstrated

1. **Type Safety**: Enum-based approach prevents runtime errors
2. **Clean Code**: Modern Java syntax for better readability
3. **Extensibility**: Easy to add new payment methods
4. **Maintainability**: Centralized payment creation logic
5. **Consistency**: Uniform interface across all payment types
6. **Error Handling**: Clear feedback for unsupported payment types

## ❌ Considerations

1. **Stateless Payments**: Current implementation doesn't handle payment state
2. **Configuration**: No external configuration for payment providers
3. **Validation**: No input validation for payment amounts
4. **Security**: No encryption or security measures implemented
5. **Error Recovery**: No retry mechanisms for failed payments

## 🎓 Interview Deep Dive

### Core Questions

**Q**: Why use Factory pattern for payment systems?  
**A**: It decouples payment processing logic from specific payment implementations, making it easy to add new payment methods without modifying existing code.

**Q**: What's the advantage of using enum over string constants?  
**A**: Enums provide compile-time type safety, prevent typos, enable IDE auto-completion, and make refactoring safer.

**Q**: How would you handle payment method-specific parameters?  
**A**: Use Builder pattern or method overloading to handle different parameter sets for different payment types.

### Advanced Scenarios

**Q**: How would you implement payment validation?  
```java
public interface PaymentValidator {
    boolean validate(double amount, Map<String, Object> paymentData);
}

public class CreditCardPayment implements Payment {
    private final PaymentValidator validator = new CreditCardValidator();
    
    @Override
    public void pay(double amount) {
        if (!validator.validate(amount, getPaymentData())) {
            throw new PaymentValidationException("Invalid payment data");
        }
        // Process payment
    }
}
```

**Q**: How would you handle async payment processing?  
```java
public interface AsyncPayment {
    CompletableFuture<PaymentResult> payAsync(double amount);
}

public class PayPalPayment implements Payment, AsyncPayment {
    @Override
    public CompletableFuture<PaymentResult> payAsync(double amount) {
        return CompletableFuture.supplyAsync(() -> {
            // Async payment processing
            return new PaymentResult(PaymentStatus.SUCCESS, "Transaction ID: " + UUID.randomUUID());
        });
    }
}
```

**Q**: How would you implement payment fee calculation?  
```java
public interface PaymentWithFees extends Payment {
    double calculateFees(double amount);
    PaymentResult payWithFees(double amount);
}

public class PayPalPayment implements PaymentWithFees {
    @Override
    public double calculateFees(double amount) {
        return amount * 0.029 + 0.30; // PayPal's fee structure
    }
    
    @Override
    public PaymentResult payWithFees(double amount) {
        double fees = calculateFees(amount);
        double total = amount + fees;
        // Process payment with fees
        return new PaymentResult(PaymentStatus.SUCCESS, total, fees);
    }
}
```

## 🚀 Production Enhancements

### Security Considerations
```java
public class SecurePaymentFactory {
    public static Payment getPaymentMethod(PaymentType type, SecurityContext context) {
        // Validate security context
        if (!context.isAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }
        
        // Apply security decorators
        Payment basePayment = PaymentFactory.getPaymentMethod(type);
        return new SecurePaymentDecorator(basePayment, context);
    }
}
```

### Monitoring and Logging
```java
public class MonitoredPaymentFactory {
    private static final Logger logger = LoggerFactory.getLogger(MonitoredPaymentFactory.class);
    private static final MeterRegistry meterRegistry = Metrics.globalRegistry;
    
    public static Payment getPaymentMethod(PaymentType type) {
        logger.info("Creating payment method: {}", type);
        
        Payment payment = PaymentFactory.getPaymentMethod(type);
        
        // Wrap with monitoring
        return new MonitoredPayment(payment, type, meterRegistry);
    }
}
```

### Configuration Management
```java
@Configuration
public class PaymentConfiguration {
    @Value("${payment.providers.enabled}")
    private List<PaymentType> enabledProviders;
    
    @Bean
    public PaymentFactory paymentFactory() {
        return new ConfigurablePaymentFactory(enabledProviders);
    }
}
```

## 🔗 Related Patterns

- **Strategy Pattern**: For different payment processing algorithms
- **Decorator Pattern**: For adding features like logging, security
- **Builder Pattern**: For complex payment object construction
- **Observer Pattern**: For payment event notifications
- **State Pattern**: For managing payment transaction states

## 📚 Real-World Applications

- **E-commerce Platforms**: Amazon, eBay payment processing
- **Financial Apps**: PayPal, Stripe, Razorpay integration
- **Mobile Wallets**: Google Pay, Apple Pay implementations
- **Banking Systems**: Multi-channel payment processing
- **Subscription Services**: Recurring payment management

---

💡 **Key Takeaway**: The Factory pattern with enum-based type safety provides a robust foundation for extensible payment systems while maintaining clean architecture and type safety.
