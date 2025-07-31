package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public class EcommerceCheckout {
    private final PaymentProcessor paymentProcessor;
    public EcommerceCheckout(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
