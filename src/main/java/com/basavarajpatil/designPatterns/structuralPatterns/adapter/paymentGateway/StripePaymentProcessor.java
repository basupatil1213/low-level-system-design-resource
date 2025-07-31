package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public class StripePaymentProcessor implements PaymentProcessor{
    @Override
    public void processPayment(double payment) {
        System.out.println("Stripe payment processed: " + payment);
    }
}
