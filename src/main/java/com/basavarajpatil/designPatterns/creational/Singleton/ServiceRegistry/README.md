# 🗂️ Service Registry Singleton

A thread-safe service registry implementation using the Singleton pattern to manage and discover application services centrally.

## 🎯 Use Case

A Service Registry is essential for:
- **Service Discovery**: Finding services by name
- **Dependency Management**: Central repository for service instances
- **Service Lifecycle**: Managing registration and unregistration
- **Loose Coupling**: Services don't need to know about each other directly
- **Configuration**: Single point for service management

## 🏗️ Implementation Details

### Bill Pugh Singleton Pattern
Uses the initialization-on-demand holder idiom for:
- **Thread Safety**: JVM class loading guarantees
- **Lazy Loading**: Created only when needed
- **Performance**: No synchronization overhead
- **Memory Efficiency**: Single registry instance

### Thread-Safe Storage
```java
Map<String, Service> services = new ConcurrentHashMap<>();
```
- **ConcurrentHashMap**: Thread-safe without external synchronization
- **Atomic Operations**: Registration/unregistration are atomic
- **Performance**: Better than synchronized collections

## 🔧 Core Operations

### Service Registration
```java
public void registerService(Service service)
```
- Adds service to the registry
- Uses service name as key
- Thread-safe operation
- Provides confirmation feedback

### Service Unregistration
```java
public void unregisterService(String name)
```
- Removes service from registry
- Handles non-existent services gracefully
- Thread-safe operation
- Provides confirmation feedback

### Service Discovery
```java
public Service getServiceByName(String name) throws Exception
```
- Retrieves service by name
- Throws `ServiceNotFoundException` if not found
- Thread-safe read operation
- Fast lookup using HashMap

### Service Listing
```java
public List<Service> listAllServices()
```
- Returns all registered services
- Creates defensive copy to prevent external modification
- Thread-safe operation
- Useful for monitoring and debugging

## 🏷️ Service Entity

### Service Class
```java
public class Service {
    private final String name;
    private final UUID uuid;
}
```

**Features:**
- **Immutable**: Thread-safe by design
- **Unique ID**: UUID for service identification
- **Name-based**: Human-readable service names
- **Descriptive**: Clear toString() implementation

## 🧪 Usage Examples

### Basic Service Management
```java
ServiceRegistry registry = ServiceRegistry.getInstance();

// Create and register services
Service userService = new Service("UserService");
Service paymentService = new Service("PaymentService");

registry.registerService(userService);
registry.registerService(paymentService);

// Discover services
try {
    Service found = registry.getServiceByName("UserService");
    System.out.println("Found: " + found);
} catch (ServiceNotFoundException e) {
    System.out.println("Service not found: " + e.getMessage());
}

// List all services
List<Service> allServices = registry.listAllServices();
allServices.forEach(System.out::println);

// Unregister service
registry.unregisterService("PaymentService");
```

### Dependency Injection Pattern
```java
public class OrderService {
    private final Service userService;
    private final Service paymentService;
    
    public OrderService() throws Exception {
        ServiceRegistry registry = ServiceRegistry.getInstance();
        this.userService = registry.getServiceByName("UserService");
        this.paymentService = registry.getServiceByName("PaymentService");
    }
    
    public void processOrder(Order order) {
        // Use discovered services
        userService.validateUser(order.getUserId());
        paymentService.processPayment(order.getPayment());
    }
}
```

## ✅ Advantages

1. **Central Management**: Single point for service discovery
2. **Loose Coupling**: Services don't need direct references
3. **Dynamic Discovery**: Services can be registered/unregistered at runtime
4. **Thread Safety**: Safe for concurrent access
5. **Simple API**: Easy to use and understand
6. **Extensible**: Easy to add features like service metadata

## ❌ Limitations

1. **Single Point of Failure**: Registry failure affects entire system
2. **Global State**: Shared state across application
3. **Testing Complexity**: Hard to isolate in unit tests
4. **Memory Leaks**: Services must be explicitly unregistered
5. **No Persistence**: Registry state lost on restart

## 🔄 Real-World Enhancements

### With Service Metadata
```java
public class EnhancedService {
    private final String name;
    private final String version;
    private final String endpoint;
    private final Map<String, String> metadata;
    private final ServiceStatus status;
}
```

### With Health Checks
```java
public interface HealthCheckable {
    boolean isHealthy();
}

public class ServiceRegistry {
    public List<Service> getHealthyServices() {
        return services.values().stream()
            .filter(service -> service instanceof HealthCheckable)
            .filter(service -> ((HealthCheckable) service).isHealthy())
            .collect(Collectors.toList());
    }
}
```

### With Service Events
```java
public class ServiceRegistry {
    private final List<ServiceRegistryListener> listeners = new ArrayList<>();
    
    public void addListener(ServiceRegistryListener listener) {
        listeners.add(listener);
    }
    
    private void notifyServiceRegistered(Service service) {
        listeners.forEach(listener -> listener.onServiceRegistered(service));
    }
}
```

## 🎓 Interview Questions

**Q**: Why use Singleton for Service Registry?  
**A**: Provides centralized service management, global access, and ensures all components use the same registry instance.

**Q**: How do you handle concurrent access to the registry?  
**A**: Uses ConcurrentHashMap for thread-safe operations without external synchronization.

**Q**: What happens if a service is not found?  
**A**: Throws ServiceNotFoundException with descriptive message indicating which service was not found.

**Q**: How would you prevent memory leaks in this registry?  
**A**: Implement proper cleanup, use weak references, or add automatic service expiration with timeouts.

**Q**: How would you scale this for distributed systems?  
**A**: Use distributed service registries like Consul, Eureka, or etcd, or implement registry clustering.

## 🔗 Related Patterns

- **Observer**: For notifying listeners about service changes
- **Factory**: For creating services with different configurations
- **Proxy**: For adding service discovery logic to clients
- **Decorator**: For adding features like caching, monitoring
- **Registry**: This is an implementation of the Registry pattern itself

## 🌐 Production Alternatives

- **Spring Framework**: `ApplicationContext` and `@Autowired`
- **Google Guice**: Dependency injection framework
- **Apache Camel**: Service registry and routing
- **Consul**: Distributed service discovery
- **Eureka**: Netflix service discovery
- **Kubernetes**: Service discovery via DNS and labels
