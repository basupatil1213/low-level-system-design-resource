package com.basavarajpatil.designPatterns.creational.factory.PaymentSystem;

public class PayPalPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}
