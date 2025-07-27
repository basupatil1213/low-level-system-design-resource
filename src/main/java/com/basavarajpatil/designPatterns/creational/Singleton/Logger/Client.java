package com.basavarajpatil.designPatterns.creational.Singleton.Logger;

public class Client {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.info("This is a info message");
    }
}
