package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class WindowsOSCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("Created Windows OS Checkbox");
    }
}
