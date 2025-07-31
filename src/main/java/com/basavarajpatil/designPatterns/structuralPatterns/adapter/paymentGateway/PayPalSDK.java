package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.Map;

/**
 * External PayPal SDK interface with its own API structure.
 * This represents a third-party payment service that we need to adapt
 * to work with our PaymentProcessor interface.
 * 
 * Note: This is an "Adaptee" in the Adapter pattern - the existing interface
 * that needs to be adapted to work with our target interface.
 */
public interface PayPalSDK {
    /**
     * PayPal's method for creating payment transactions
     * @param paymentData Map containing payment details with PayPal-specific keys
     * @return PayPal-specific transaction response
     */
    PayPalTransactionResponse createPayment(Map<String, Object> paymentData);
    
    /**
     * PayPal's method for executing authorized payments
     * @param paymentId PayPal payment identifier
     * @param payerId PayPal payer identifier
     * @return PayPal-specific execution response
     */
    PayPalTransactionResponse executePayment(String paymentId, String payerId);
    
    /**
     * PayPal's method for processing refunds
     * @param transactionId Original transaction ID
     * @param refundAmount Amount to refund
     * @param currency Currency code
     * @return PayPal-specific refund response
     */
    PayPalRefundResponse processRefund(String transactionId, double refundAmount, String currency);
    
    /**
     * PayPal's method for validating payment details
     * @param amount Payment amount
     * @param currencyCode Currency code
     * @param merchantId Merchant identifier
     * @return validation result
     */
    boolean validatePaymentDetails(double amount, String currencyCode, String merchantId);
    
    /**
     * PayPal-specific transaction response
     */
    class PayPalTransactionResponse {
        private final String paymentId;
        private final String status;
        private final String message;
        private final double amount;
        private final String currency;
        
        public PayPalTransactionResponse(String paymentId, String status, String message, double amount, String currency) {
            this.paymentId = paymentId;
            this.status = status;
            this.message = message;
            this.amount = amount;
            this.currency = currency;
        }
        
        public String getPaymentId() { return paymentId; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }
    }
    
    /**
     * PayPal-specific refund response
     */
    class PayPalRefundResponse {
        private final String refundId;
        private final String status;
        private final String message;
        private final double amount;
        
        public PayPalRefundResponse(String refundId, String status, String message, double amount) {
            this.refundId = refundId;
            this.status = status;
            this.message = message;
            this.amount = amount;
        }
        
        public String getRefundId() { return refundId; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
        public double getAmount() { return amount; }
    }
}
