package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.ArrayList;
import java.util.List;

/**
 * E-commerce checkout system that uses PaymentProcessor interface.
 * This is the client in the Adapter pattern - it knows only about the target interface
 * and remains unaware of the specific payment providers or their adapters.
 * 
 * This demonstrates how the client code stays clean and focused on business logic
 * while the adapters handle the complexity of integrating with different payment services.
 */
public class EcommerceCheckout {
    private final PaymentProcessor paymentProcessor;
    private final String merchantId;
    private final List<PaymentResult> transactionHistory;
    
    public EcommerceCheckout(PaymentProcessor paymentProcessor, String merchantId) {
        this.paymentProcessor = paymentProcessor;
        this.merchantId = merchantId;
        this.transactionHistory = new ArrayList<>();
    }
    
    /**
     * Processes a checkout payment
     * @param amount Payment amount
     * @param currency Currency code
     * @return Payment result
     */
    public PaymentResult checkout(double amount, String currency) {
        System.out.println("\n=== E-commerce Checkout Started ===");
        System.out.println("Amount: " + amount + " " + currency);
        System.out.println("Payment Provider: " + paymentProcessor.getProviderName());
        System.out.println("Merchant ID: " + merchantId);
        
        // Validate payment before processing
        if (!paymentProcessor.validatePayment(amount, currency)) {
            PaymentResult failureResult = PaymentResult.failure(amount, currency, 
                paymentProcessor.getProviderName(), "Pre-checkout validation failed");
            transactionHistory.add(failureResult);
            System.out.println("Checkout Result: " + failureResult);
            return failureResult;
        }
        
        // Process the payment
        PaymentResult result = paymentProcessor.processPayment(amount, currency, merchantId);
        transactionHistory.add(result);
        
        System.out.println("Checkout Result: " + result);
        System.out.println("=== E-commerce Checkout Completed ===\n");
        
        return result;
    }
    
    /**
     * Processes a refund for a previous transaction
     * @param transactionId Original transaction ID
     * @param amount Refund amount
     * @return Refund result
     */
    public PaymentResult processRefund(String transactionId, double amount) {
        System.out.println("\n=== Processing Refund ===");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Refund Amount: " + amount);
        System.out.println("Payment Provider: " + paymentProcessor.getProviderName());
        
        PaymentResult refundResult = paymentProcessor.processRefund(transactionId, amount);
        transactionHistory.add(refundResult);
        
        System.out.println("Refund Result: " + refundResult);
        System.out.println("=== Refund Processing Completed ===\n");
        
        return refundResult;
    }
    
    /**
     * Gets the payment provider name
     * @return Provider name
     */
    public String getPaymentProviderName() {
        return paymentProcessor.getProviderName();
    }
    
    /**
     * Gets transaction history
     * @return List of all transactions
     */
    public List<PaymentResult> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    /**
     * Prints transaction summary
     */
    public void printTransactionSummary() {
        System.out.println("\n=== Transaction Summary ===");
        System.out.println("Payment Provider: " + paymentProcessor.getProviderName());
        System.out.println("Total Transactions: " + transactionHistory.size());
        
        double totalSuccessful = transactionHistory.stream()
                .filter(PaymentResult::isSuccess)
                .mapToDouble(PaymentResult::getAmount)
                .sum();
        
        long successfulCount = transactionHistory.stream()
                .filter(PaymentResult::isSuccess)
                .count();
        
        System.out.println("Successful Transactions: " + successfulCount);
        System.out.println("Total Successful Amount: $" + String.format("%.2f", totalSuccessful));
        System.out.println("=== End Summary ===\n");
    }
}
