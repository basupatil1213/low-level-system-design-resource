package com.basavarajpatil.designPatterns.creational.Singleton.ServiceRegistry;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String name) {
        super("Service with name " + name + " not found");
    }
}
