package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class WindowsOSButton implements Button{
    @Override
    public void paint() {
        System.out.println("Created Windows OS button");
    }
}
