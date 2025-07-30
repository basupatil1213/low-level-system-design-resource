package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckBox();
    }

    public void paint(){
        button.paint();
        checkbox.paint();
    }
}
