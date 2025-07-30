package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsOSButton();
    }

    @Override
    public Checkbox createCheckBox() {
        return new WindowsOSCheckbox();
    }
}
