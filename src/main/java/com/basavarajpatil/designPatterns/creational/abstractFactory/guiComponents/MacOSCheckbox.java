package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class MacOSCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("Created MacOS Checkbox");
    }
}
