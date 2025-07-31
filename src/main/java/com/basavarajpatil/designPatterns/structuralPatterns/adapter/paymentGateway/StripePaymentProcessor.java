package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.UUID;

/**
 * Direct implementation of PaymentProcessor for Stripe.
 * This shows that some payment providers may already have compatible interfaces
 * and don't need an adapter. Stripe's API design happens to align well with
 * our PaymentProcessor interface.
 * 
 * This demonstrates that the Adapter pattern is only needed when interfaces
 * are incompatible - not all external services require adaptation.
 */
public class StripePaymentProcessor implements PaymentProcessor {
    private static final String PROVIDER_NAME = "Stripe";
    
    @Override
    public PaymentResult processPayment(double amount, String currency, String merchantId) {
        try {
            System.out.println("Stripe: Processing payment for " + amount + " " + currency + 
                             " (Merchant: " + merchantId + ")");
            
            // Validate payment
            if (!validatePayment(amount, currency)) {
                return PaymentResult.failure(amount, currency, PROVIDER_NAME, "Payment validation failed");
            }
            
            // Simulate Stripe payment processing
            if (amount > 10000) {
                return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                    "Amount exceeds transaction limit");
            }
            
            String transactionId = "pi_" + UUID.randomUUID().toString().replace("-", "").substring(0, 24);
            System.out.println("Stripe: Payment processed successfully with ID: " + transactionId);
            
            return new PaymentResult.Builder()
                    .transactionId(transactionId)
                    .success(true)
                    .amount(amount)
                    .currency(currency)
                    .providerName(PROVIDER_NAME)
                    .status(PaymentResult.PaymentStatus.SUCCESS)
                    .message("Stripe payment processed successfully")
                    .build();
                    
        } catch (Exception e) {
            return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                "Stripe processing error: " + e.getMessage());
        }
    }
    
    @Override
    public PaymentResult processRefund(String transactionId, double amount) {
        try {
            System.out.println("Stripe: Processing refund for transaction " + transactionId + 
                             " - Amount: " + amount);
            
            if (amount <= 0) {
                return PaymentResult.failure(amount, "USD", PROVIDER_NAME, "Invalid refund amount");
            }
            
            String refundId = "re_" + UUID.randomUUID().toString().replace("-", "").substring(0, 24);
            System.out.println("Stripe: Refund processed successfully with ID: " + refundId);
            
            return new PaymentResult.Builder()
                    .transactionId(refundId)
                    .success(true)
                    .amount(amount)
                    .currency("USD")
                    .providerName(PROVIDER_NAME)
                    .status(PaymentResult.PaymentStatus.REFUNDED)
                    .message("Stripe refund processed successfully")
                    .build();
                    
        } catch (Exception e) {
            return PaymentResult.failure(amount, "USD", PROVIDER_NAME, 
                "Stripe refund error: " + e.getMessage());
        }
    }
    
    @Override
    public boolean validatePayment(double amount, String currency) {
        System.out.println("Stripe: Validating payment details");
        
        boolean valid = amount > 0 && 
                       amount <= 10000 && // Stripe limit simulation
                       currency != null && 
                       !currency.isEmpty();
        
        System.out.println("Stripe: Validation result: " + (valid ? "PASSED" : "FAILED"));
        return valid;
    }
    
    @Override
    public String getProviderName() {
        return PROVIDER_NAME;
    }
}
