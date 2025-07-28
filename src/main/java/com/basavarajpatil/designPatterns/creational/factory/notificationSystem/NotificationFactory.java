package com.basavarajpatil.designPatterns.creational.factory.notificationSystem;

public class NotificationFactory {

    public static Notification getNotification(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailNotification();
        }
        if (type.equalsIgnoreCase("sms")) {
            return new SMSNotification();
        }

        throw new IllegalArgumentException("Notification type not supported: " + type);
    }
}
