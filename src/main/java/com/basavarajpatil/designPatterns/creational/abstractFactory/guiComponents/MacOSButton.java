package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class MacOSButton implements Button{
    @Override
    public void paint() {
        System.out.println("Created MacOS button");
    }
}
