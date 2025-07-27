package com.basavarajpatil.designPatterns.creational.Singleton.ServiceRegistry;

public class Client {
    public static void main(String[] args) {
        ServiceRegistry registry = ServiceRegistry.getInstance();

        Service userService = new Service("UserService");
        Service paymentService = new Service("PaymentService");

        registry.registerService(userService);
        registry.registerService(paymentService);

        try {
            System.out.println(registry.getServiceByName("UserService"));
            System.out.println(registry.getServiceByName("OrderService")); // Throws exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        registry.unregisterService("PaymentService");
    }
}
