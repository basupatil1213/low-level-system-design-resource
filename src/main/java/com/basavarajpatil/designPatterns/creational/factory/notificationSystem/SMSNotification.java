package com.basavarajpatil.designPatterns.creational.factory.notificationSystem;

public class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
