package com.basavarajpatil.lldMockInterviews.easy.vendingMachine;

public class Item {
    public enum Type {
        DRINK, SNACK, CHOCOLATE
    }

    private final String name;
    private final Type type;

    public Item(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Item[name=" + name + ", type=" + type + "]";
    }
}
