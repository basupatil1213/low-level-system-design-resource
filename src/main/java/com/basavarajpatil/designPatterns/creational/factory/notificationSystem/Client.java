package com.basavarajpatil.designPatterns.creational.factory.notificationSystem;

public class Client {
    public static void main(String[] args) {
        try{
            // Email Notification
            Notification emailNotification = NotificationFactory.getNotification("email");
            emailNotification.send("User created successfully!");

            // SMS Notification
            Notification smsNotification = NotificationFactory.getNotification("sms");
            smsNotification.send("User created successfully!");

            // Error
            Notification pushNotification = NotificationFactory.getNotification("push");
            pushNotification.send("User created successfully!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
