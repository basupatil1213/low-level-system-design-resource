package com.basavarajpatil.designPatterns.creational.factory.PaymentSystem;

public class CreditCardPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via credit card");
    }
}
