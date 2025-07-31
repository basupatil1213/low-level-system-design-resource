package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public class PayPalPaymentProcessor implements PayPalSDK{
    @Override
    public void makeTransaction(double amount) {
        System.out.println("Made payment through PayPal: " + amount);
    }
}
