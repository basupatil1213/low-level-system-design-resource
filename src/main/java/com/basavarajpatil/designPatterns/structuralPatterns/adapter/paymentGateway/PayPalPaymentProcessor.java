package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.Map;
import java.util.UUID;

/**
 * Concrete implementation of PayPal SDK.
 * This simulates the actual PayPal payment processing service.
 * In a real scenario, this would be provided by PayPal's SDK library.
 */
public class PayPalPaymentProcessor implements PayPalSDK {
    
    @Override
    public PayPalTransactionResponse createPayment(Map<String, Object> paymentData) {
        // Simulate PayPal payment creation
        double amount = (Double) paymentData.get("amount");
        String currency = (String) paymentData.get("currency");
        String merchantId = (String) paymentData.get("merchant_id");
        
        System.out.println("PayPal SDK: Creating payment for " + amount + " " + currency + " (Merchant: " + merchantId + ")");
        
        // Simulate some processing time and validation
        if (amount <= 0) {
            return new PayPalTransactionResponse(null, "FAILED", "Invalid amount", amount, currency);
        }
        
        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
        System.out.println("PayPal SDK: Payment created with ID: " + paymentId);
        
        return new PayPalTransactionResponse(paymentId, "CREATED", "Payment created successfully", amount, currency);
    }
    
    @Override
    public PayPalTransactionResponse executePayment(String paymentId, String payerId) {
        System.out.println("PayPal SDK: Executing payment " + paymentId + " for payer " + payerId);
        
        // Simulate payment execution
        if (paymentId == null || paymentId.isEmpty()) {
            return new PayPalTransactionResponse(null, "FAILED", "Invalid payment ID", 0, "USD");
        }
        
        System.out.println("PayPal SDK: Payment executed successfully");
        return new PayPalTransactionResponse(paymentId, "APPROVED", "Payment executed successfully", 0, "USD");
    }
    
    @Override
    public PayPalRefundResponse processRefund(String transactionId, double refundAmount, String currency) {
        System.out.println("PayPal SDK: Processing refund for transaction " + transactionId + 
                         " - Amount: " + refundAmount + " " + currency);
        
        if (refundAmount <= 0) {
            return new PayPalRefundResponse(null, "FAILED", "Invalid refund amount", refundAmount);
        }
        
        String refundId = "REF-" + UUID.randomUUID().toString().substring(0, 12).toUpperCase();
        System.out.println("PayPal SDK: Refund processed with ID: " + refundId);
        
        return new PayPalRefundResponse(refundId, "COMPLETED", "Refund processed successfully", refundAmount);
    }
    
    @Override
    public boolean validatePaymentDetails(double amount, String currencyCode, String merchantId) {
        System.out.println("PayPal SDK: Validating payment details");
        
        // Basic validation logic
        boolean valid = amount > 0 && 
                       currencyCode != null && !currencyCode.isEmpty() && 
                       merchantId != null && !merchantId.isEmpty();
        
        System.out.println("PayPal SDK: Validation result: " + (valid ? "PASSED" : "FAILED"));
        return valid;
    }
}
