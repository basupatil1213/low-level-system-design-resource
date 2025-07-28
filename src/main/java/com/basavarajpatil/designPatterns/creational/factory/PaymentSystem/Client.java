package com.basavarajpatil.designPatterns.creational.factory.PaymentSystem;

public class Client {
    public static void main(String[] args) {
        try{
            Payment paypalPayment = PaymentFactory.getPaymentMethod(PaymentType.PAYPAL);
            paypalPayment.pay(143);

            Payment upiPayment = PaymentFactory.getPaymentMethod(PaymentType.UPI);
            upiPayment.pay(286);

            Payment creditCardPayment = PaymentFactory.getPaymentMethod(PaymentType.CREDITCARD);
            creditCardPayment.pay(143);

            // Should throw error
            Payment debitCardPayment = PaymentFactory.getPaymentMethod(PaymentType.DEBITCARD);
            debitCardPayment.pay(286);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
