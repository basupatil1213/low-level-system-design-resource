package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public class PayPalAdapter implements PaymentProcessor{
    private final PayPalSDK payPalSDK;
    public PayPalAdapter(PayPalSDK payPalSDK) {
        this.payPalSDK = payPalSDK;
    }
    @Override
    public void processPayment(double payment) {
        this.payPalSDK.makeTransaction(payment);
    }
}
