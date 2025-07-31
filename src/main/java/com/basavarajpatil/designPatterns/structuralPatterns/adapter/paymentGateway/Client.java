package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.Arrays;
import java.util.List;

/**
 * Client application demonstrating the Adapter pattern with payment gateways.
 * This class shows how different payment providers can be used interchangeably
 * through the common PaymentProcessor interface, regardless of their underlying APIs.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("💳 Payment Gateway Adapter Pattern Demo");
        System.out.println("=" .repeat(50));
        
        // Demonstrate basic payment processing
        demonstrateBasicPayments();
        
        // Demonstrate refund processing
        demonstrateRefunds();
        
        // Demonstrate validation scenarios
        demonstrateValidationScenarios();
        
        // Demonstrate error handling
        demonstrateErrorHandling();
        
        // Show transaction summaries
        demonstrateTransactionSummaries();
    }
    
    /**
     * Demonstrates basic payment processing with different providers
     */
    private static void demonstrateBasicPayments() {
        System.out.println("\n1️⃣ BASIC PAYMENT PROCESSING DEMONSTRATION");
        System.out.println("-".repeat(45));
        
        // Create different payment processors
        PaymentProcessor stripeProcessor = new StripePaymentProcessor();
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalPaymentProcessor());
        PaymentProcessor squareProcessor = new SquareAdapter(new SquarePaymentService());
        
        List<PaymentProcessor> processors = Arrays.asList(stripeProcessor, paypalProcessor, squareProcessor);
        
        // Process payments with each provider
        for (PaymentProcessor processor : processors) {
            EcommerceCheckout checkout = new EcommerceCheckout(processor, "MERCHANT-123");
            checkout.checkout(99.99, "USD");
        }
    }
    
    /**
     * Demonstrates refund processing
     */
    private static void demonstrateRefunds() {
        System.out.println("\n2️⃣ REFUND PROCESSING DEMONSTRATION");
        System.out.println("-".repeat(40));
        
        // Process a payment and then refund it
        PaymentProcessor stripeProcessor = new StripePaymentProcessor();
        EcommerceCheckout stripeCheckout = new EcommerceCheckout(stripeProcessor, "MERCHANT-123");
        
        // Make a payment
        PaymentResult paymentResult = stripeCheckout.checkout(150.00, "USD");
        
        // Process refund if payment was successful
        if (paymentResult.isSuccess()) {
            stripeCheckout.processRefund(paymentResult.getTransactionId(), 150.00);
        }
        
        // Demonstrate refund with PayPal adapter
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalPaymentProcessor());
        EcommerceCheckout paypalCheckout = new EcommerceCheckout(paypalProcessor, "MERCHANT-456");
        
        PaymentResult paypalPayment = paypalCheckout.checkout(75.50, "USD");
        if (paypalPayment.isSuccess()) {
            paypalCheckout.processRefund(paypalPayment.getTransactionId(), 75.50);
        }
    }
    
    /**
     * Demonstrates validation scenarios
     */
    private static void demonstrateValidationScenarios() {
        System.out.println("\n3️⃣ VALIDATION SCENARIOS DEMONSTRATION");
        System.out.println("-".repeat(42));
        
        PaymentProcessor stripeProcessor = new StripePaymentProcessor();
        EcommerceCheckout checkout = new EcommerceCheckout(stripeProcessor, "MERCHANT-123");
        
        // Test various validation scenarios
        System.out.println("Testing various validation scenarios:");
        
        // Valid payment
        checkout.checkout(50.00, "USD");
        
        // Invalid amount (negative)
        checkout.checkout(-10.00, "USD");
        
        // Invalid amount (too large for Stripe)
        checkout.checkout(15000.00, "USD");
        
        // Invalid currency (empty)
        checkout.checkout(25.00, "");
    }
    
    /**
     * Demonstrates error handling
     */
    private static void demonstrateErrorHandling() {
        System.out.println("\n4️⃣ ERROR HANDLING DEMONSTRATION");
        System.out.println("-".repeat(38));
        
        // Test Square with amount exceeding limits
        PaymentProcessor squareProcessor = new SquareAdapter(new SquarePaymentService());
        EcommerceCheckout squareCheckout = new EcommerceCheckout(squareProcessor, "MERCHANT-789");
        
        System.out.println("Testing Square payment limits:");
        squareCheckout.checkout(6000.00, "USD"); // Should exceed Square's $5000 limit
        
        // Test invalid refund
        System.out.println("Testing invalid refund scenarios:");
        squareCheckout.processRefund("INVALID-TXN-ID", 100.00);
        squareCheckout.processRefund("", -50.00);
    }
    
    /**
     * Demonstrates transaction summaries
     */
    private static void demonstrateTransactionSummaries() {
        System.out.println("\n5️⃣ TRANSACTION SUMMARIES");
        System.out.println("-".repeat(30));
        
        // Create checkouts with different processors
        EcommerceCheckout stripeCheckout = new EcommerceCheckout(
            new StripePaymentProcessor(), "MERCHANT-STRIPE");
        EcommerceCheckout paypalCheckout = new EcommerceCheckout(
            new PayPalAdapter(new PayPalPaymentProcessor()), "MERCHANT-PAYPAL");
        EcommerceCheckout squareCheckout = new EcommerceCheckout(
            new SquareAdapter(new SquarePaymentService()), "MERCHANT-SQUARE");
        
        // Process multiple transactions
        stripeCheckout.checkout(100.00, "USD");
        stripeCheckout.checkout(250.00, "USD");
        stripeCheckout.checkout(-10.00, "USD"); // Should fail
        
        paypalCheckout.checkout(75.00, "USD");
        paypalCheckout.checkout(125.00, "USD");
        
        squareCheckout.checkout(200.00, "USD");
        squareCheckout.checkout(300.00, "USD");
        
        // Print summaries
        stripeCheckout.printTransactionSummary();
        paypalCheckout.printTransactionSummary();
        squareCheckout.printTransactionSummary();
    }
}
