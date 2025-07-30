package com.basavarajpatil.designPatterns.creational.abstractFactory.guiComponents;

public class Client {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name").toLowerCase();
        GUIFactory factory;
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        }
        else if (osName.contains("mac")) {
            factory = new MacOSFactory();
        }
        else {
            throw new RuntimeException("Unsupported OS: " + osName);
        }

        Application application = new Application(factory);
        application.paint();

    }
}
