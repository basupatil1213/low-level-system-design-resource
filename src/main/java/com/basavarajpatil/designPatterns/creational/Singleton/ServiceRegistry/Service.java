package com.basavarajpatil.designPatterns.creational.Singleton.ServiceRegistry;

import java.util.UUID;

public class Service {
    private final String name;
    private final UUID uuid;

    public Service(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Service [name=" + name + ", uuid=" + uuid + "]";
    }
}
