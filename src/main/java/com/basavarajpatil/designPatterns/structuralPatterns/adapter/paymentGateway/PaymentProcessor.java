package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

/**
 * Target interface that defines the standard payment processing contract
 * expected by the client application. This is the interface that all payment
 * processors must implement to be compatible with our e-commerce system.
 */
public interface PaymentProcessor {
    /**
     * Processes a payment transaction
     * @param amount The amount to be processed
     * @param currency The currency code (e.g., "USD", "EUR")
     * @param merchantId The merchant identifier
     * @return PaymentResult containing transaction details
     */
    PaymentResult processPayment(double amount, String currency, String merchantId);
    
    /**
     * Processes a refund for a previous transaction
     * @param transactionId The original transaction ID
     * @param amount The amount to refund
     * @return PaymentResult containing refund details
     */
    PaymentResult processRefund(String transactionId, double amount);
    
    /**
     * Validates payment details before processing
     * @param amount The payment amount
     * @param currency The currency code
     * @return true if validation passes
     */
    boolean validatePayment(double amount, String currency);
    
    /**
     * Gets the name of the payment provider
     * @return Provider name
     */
    String getProviderName();
}
