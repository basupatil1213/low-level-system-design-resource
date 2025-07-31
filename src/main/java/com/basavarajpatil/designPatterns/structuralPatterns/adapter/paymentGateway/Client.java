package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

public class Client {
    public static void main(String[] args) {

        PaymentProcessor stipePaymentProcessor = new StripePaymentProcessor();
        EcommerceCheckout stripeCheckout = new EcommerceCheckout(stipePaymentProcessor);
        stripeCheckout.checkout(200);

        PaymentProcessor paypalPaymentProcessor = new PayPalAdapter(new PayPalPaymentProcessor());
        EcommerceCheckout paypalCheckout = new EcommerceCheckout(paypalPaymentProcessor);
        paypalCheckout.checkout(400);
    }
}
