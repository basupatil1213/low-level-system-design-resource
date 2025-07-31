package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

/**
 * Square Adapter that adapts Square's payment API to our PaymentProcessor interface.
 * This demonstrates another adapter implementation with different adaptation challenges:
 * 
 * 1. Unit conversion: Square works with cents, we work with dollars
 * 2. Different method names: Square uses "chargePayment", we use "processPayment"  
 * 3. Different parameter structures: Square needs locationId and orderReference
 * 4. Different response formats: Square returns different response objects
 */
public class SquareAdapter implements PaymentProcessor {
    private final SquarePaymentAPI squareAPI;
    private static final String PROVIDER_NAME = "Square";
    private static final String DEFAULT_LOCATION_ID = "LOC123456789";
    
    public SquareAdapter(SquarePaymentAPI squareAPI) {
        this.squareAPI = squareAPI;
    }
    
    @Override
    public PaymentResult processPayment(double amount, String currency, String merchantId) {
        try {
            // Convert dollars to cents (Square works with cents)
            long amountInCents = Math.round(amount * 100);
            
            // Validate using Square's eligibility check
            if (!squareAPI.checkPaymentEligibility(amountInCents, DEFAULT_LOCATION_ID)) {
                return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                    "Payment not eligible according to Square");
            }
            
            // Generate order reference (Square requirement)
            String orderReference = "ORD-" + System.currentTimeMillis();
            
            // Call Square's charge method
            SquarePaymentAPI.SquareChargeResponse response = 
                squareAPI.chargePayment(amountInCents, currency, DEFAULT_LOCATION_ID, orderReference);
            
            // Convert Square's response to our standard format
            if ("COMPLETED".equals(response.getStatus())) {
                return new PaymentResult.Builder()
                        .transactionId(response.getPaymentId())
                        .success(true)
                        .amount(amount)
                        .currency(currency)
                        .providerName(PROVIDER_NAME)
                        .status(PaymentResult.PaymentStatus.SUCCESS)
                        .message("Square payment processed successfully")
                        .build();
            } else {
                return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                    "Square payment failed: " + response.getErrorMessage());
            }
            
        } catch (Exception e) {
            return PaymentResult.failure(amount, currency, PROVIDER_NAME, 
                "Square payment error: " + e.getMessage());
        }
    }
    
    @Override
    public PaymentResult processRefund(String transactionId, double amount) {
        try {
            // Square uses "void" instead of "refund"
            // In real scenarios, you might need to check if the payment can be voided or refunded
            SquarePaymentAPI.SquareVoidResponse voidResponse = 
                squareAPI.voidPayment(transactionId, "Customer requested refund");
            
            if ("COMPLETED".equals(voidResponse.getStatus())) {
                return new PaymentResult.Builder()
                        .transactionId(voidResponse.getVoidId())
                        .success(true)
                        .amount(amount)
                        .currency("USD")
                        .providerName(PROVIDER_NAME)
                        .status(PaymentResult.PaymentStatus.REFUNDED)
                        .message("Square payment voided successfully")
                        .build();
            } else {
                return PaymentResult.failure(amount, "USD", PROVIDER_NAME, 
                    "Square void failed: " + voidResponse.getMessage());
            }
            
        } catch (Exception e) {
            return PaymentResult.failure(amount, "USD", PROVIDER_NAME, 
                "Square void error: " + e.getMessage());
        }
    }
    
    @Override
    public boolean validatePayment(double amount, String currency) {
        // Convert to cents and use Square's eligibility check
        long amountInCents = Math.round(amount * 100);
        return squareAPI.checkPaymentEligibility(amountInCents, DEFAULT_LOCATION_ID);
    }
    
    @Override
    public String getProviderName() {
        return PROVIDER_NAME;
    }
}
