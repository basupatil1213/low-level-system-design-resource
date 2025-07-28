package com.basavarajpatil.designPatterns.creational.factory.PaymentSystem;

public class UPIPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }
}
