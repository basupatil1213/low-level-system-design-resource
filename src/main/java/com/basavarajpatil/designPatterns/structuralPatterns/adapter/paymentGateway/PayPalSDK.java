package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public interface PayPalSDK {
    void makeTransaction(double amount);
}
