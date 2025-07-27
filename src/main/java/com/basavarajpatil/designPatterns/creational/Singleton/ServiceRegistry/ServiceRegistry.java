package com.basavarajpatil.designPatterns.creational.Singleton.ServiceRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceRegistry {
    Map<String, Service> services;
    private ServiceRegistry() {
        services = new ConcurrentHashMap<>();
    }

    private static class ServiceRegistryHolder{
        private static final ServiceRegistry INSTANCE = new ServiceRegistry();
    }

    public static ServiceRegistry getInstance(){
        return ServiceRegistryHolder.INSTANCE;
    }

    public void registerService(Service service){
        services.put(service.getName(), service);
        System.out.println("Registered service " + service.getName());
    }

    public void unregisterService(String name){
        services.remove(name);
        System.out.println("Unregistered service " + name);
    }

    public Service getServiceByName(String name) throws Exception {
        if (!services.containsKey(name)){
            throw new ServiceNotFoundException(name);
        }
        return services.get(name);
    }

    public List<Service> listAllServices() {
        return new ArrayList<>(services.values());
    }


}
