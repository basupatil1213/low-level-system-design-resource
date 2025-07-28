package com.basavarajpatil.designPatterns.creational.factory.PaymentSystem;

public class PaymentFactory {
    public static Payment getPaymentMethod(PaymentType paymentType) {
        switch (paymentType) {
            case UPI -> {
                return new UPIPayment();
            }
            case PAYPAL -> {
                return new PayPalPayment();
            }
            case CREDITCARD -> {
                return new CreditCardPayment();
            }
            default -> {
                throw new IllegalArgumentException("Unsupported payment type");
            }
        }
    }
}
