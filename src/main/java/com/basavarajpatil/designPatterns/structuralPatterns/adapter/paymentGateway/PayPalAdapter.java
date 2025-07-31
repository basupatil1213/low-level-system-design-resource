package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.HashMap;
import java.util.Map;

/**
 * PayPal Adapter that adapts PayPal's SDK interface to our PaymentProcessor interface.
 * This is the core of the Adapter pattern - it translates between incompatible interfaces.
 * 
 * The adapter:
 * 1. Implements the target interface (PaymentProcessor)
 * 2. Contains a reference to the adaptee (PayPalSDK)
 * 3. Translates method calls and data formats between the two interfaces
 */
public class PayPalAdapter implements PaymentProcessor {
    private final PayPalSDK payPalSDK;
    private static final String PROVIDER_NAME = "PayPal";
    
    /**
     * Constructor that takes the PayPal SDK instance to be adapted
     * @param payPalSDK The PayPal SDK instance
     */
    public PayPalAdapter(PayPalSDK payPalSDK) {
        this.payPalSDK = payPalSDK;
    }
    
    @Override
    public PaymentResult processPayment(double amount, String currency, String merchantId) {
        try {
            // Validate using PayPal's validation method
            if (!payPalSDK.validatePaymentDetails(amount, currency, merchantId)) {
                return PaymentResult.failure(amount, currency, PROVIDER_NAME, "Payment validation failed");
            }
            
            // Convert our standard parameters to PayPal's expected format
            Map<String, Object> paymentData = new HashMap<>();
            paymentData.put("amount", amount);
            paymentData.put("currency", currency);
            paymentData.put("merchant_id", merchantId);
            paymentData.put("intent", "sale");
            paymentData.put("payment_method", "paypal");
            
            // Call PayPal's createPayment method
            PayPalSDK.PayPalTransactionResponse response = payPalSDK.createPayment(paymentData);
            
            // Convert PayPal's response to our standard PaymentResult format
            if ("CREATED".equals(response.getStatus())) {
                // For simplicity, we'll execute the payment immediately
                // In real scenarios, this might involve user approval steps
                PayPalSDK.PayPalTransactionResponse executionResponse = 
                    payPalSDK.executePayment(response.getPaymentId(), "sample-payer-id");
                
                if ("APPROVED".equals(executionResponse.getStatus())) {
                    return new PaymentResult.Builder()
                            .transactionId(response.getPaymentId())
                            .success(true)
                            .amount(amount)
                            .currency(currency)
                            .providerName(PROVIDER_NAME)
                            .status(PaymentResult.PaymentStatus.SUCCESS)
                            .message("PayPal payment processed successfully")
                            .build();
                }
            }
            
            return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                "PayPal payment failed: " + response.getMessage());
            
        } catch (Exception e) {
            return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                "PayPal payment error: " + e.getMessage());
        }
    }
    
    @Override
    public PaymentResult processRefund(String transactionId, double amount) {
        try {
            // Call PayPal's refund method
            PayPalSDK.PayPalRefundResponse refundResponse = 
                payPalSDK.processRefund(transactionId, amount, "USD");
            
            // Convert PayPal's refund response to our standard format
            if ("COMPLETED".equals(refundResponse.getStatus())) {
                return new PaymentResult.Builder()
                        .transactionId(refundResponse.getRefundId())
                        .success(true)
                        .amount(amount)
                        .currency("USD")
                        .providerName(PROVIDER_NAME)
                        .status(PaymentResult.PaymentStatus.REFUNDED)
                        .message("PayPal refund processed successfully")
                        .build();
            }
            
            return PaymentResult.failure(amount, "USD", PROVIDER_NAME, 
                "PayPal refund failed: " + refundResponse.getMessage());
            
        } catch (Exception e) {
            return PaymentResult.failure(amount, "USD", PROVIDER_NAME, 
                "PayPal refund error: " + e.getMessage());
        }
    }
    
    @Override
    public boolean validatePayment(double amount, String currency) {
        // Use PayPal's validation with a dummy merchant ID
        return payPalSDK.validatePaymentDetails(amount, currency, "default-merchant");
    }
    
    @Override
    public String getProviderName() {
        return PROVIDER_NAME;
    }
}
