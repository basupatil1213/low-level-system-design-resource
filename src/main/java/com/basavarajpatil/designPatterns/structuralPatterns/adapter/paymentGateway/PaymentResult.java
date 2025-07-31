package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents the result of a payment transaction.
 * This class encapsulates all the information about a payment operation.
 */
public class PaymentResult {
    private final String transactionId;
    private final boolean success;
    private final String message;
    private final double amount;
    private final String currency;
    private final String providerName;
    private final LocalDateTime timestamp;
    private final PaymentStatus status;
    
    public enum PaymentStatus {
        SUCCESS, FAILED, PENDING, CANCELLED, REFUNDED
    }
    
    private PaymentResult(Builder builder) {
        this.transactionId = builder.transactionId;
        this.success = builder.success;
        this.message = builder.message;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.providerName = builder.providerName;
        this.timestamp = builder.timestamp != null ? builder.timestamp : LocalDateTime.now();
        this.status = builder.status;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getProviderName() { return providerName; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public PaymentStatus getStatus() { return status; }
    
    @Override
    public String toString() {
        return String.format("PaymentResult{id='%s', success=%s, amount=%.2f %s, provider='%s', status=%s, message='%s'}",
                transactionId, success, amount, currency, providerName, status, message);
    }
    
    // Builder pattern for flexible object creation
    public static class Builder {
        private String transactionId;
        private boolean success;
        private String message;
        private double amount;
        private String currency;
        private String providerName;
        private LocalDateTime timestamp;
        private PaymentStatus status;
        
        public Builder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }
        
        public Builder success(boolean success) {
            this.success = success;
            return this;
        }
        
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }
        
        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }
        
        public Builder providerName(String providerName) {
            this.providerName = providerName;
            return this;
        }
        
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }
        
        public PaymentResult build() {
            // Generate transaction ID if not provided
            if (transactionId == null) {
                transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            }
            return new PaymentResult(this);
        }
    }
    
    // Factory methods for common scenarios
    public static PaymentResult success(double amount, String currency, String providerName) {
        return new Builder()
                .success(true)
                .amount(amount)
                .currency(currency)
                .providerName(providerName)
                .status(PaymentStatus.SUCCESS)
                .message("Payment processed successfully")
                .build();
    }
    
    public static PaymentResult failure(double amount, String currency, String providerName, String reason) {
        return new Builder()
                .success(false)
                .amount(amount)
                .currency(currency)
                .providerName(providerName)
                .status(PaymentStatus.FAILED)
                .message(reason)
                .build();
    }
    
    public static PaymentResult refund(String transactionId, double amount, String currency, String providerName) {
        return new Builder()
                .transactionId(transactionId)
                .success(true)
                .amount(amount)
                .currency(currency)
                .providerName(providerName)
                .status(PaymentStatus.REFUNDED)
                .message("Refund processed successfully")
                .build();
    }
}
