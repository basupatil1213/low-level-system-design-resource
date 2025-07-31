package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

import java.util.UUID;

/**
 * Concrete implementation of Square Payment API.
 * This simulates the actual Square payment processing service.
 */
public class SquarePaymentService implements SquarePaymentAPI {
    
    @Override
    public SquareChargeResponse chargePayment(long amountInCents, String currencyCode, 
                                            String locationId, String orderReference) {
        System.out.println("Square API: Processing charge for " + amountInCents + " cents in " + 
                         currencyCode + " (Location: " + locationId + ", Order: " + orderReference + ")");
        
        // Simulate Square payment processing
        if (amountInCents <= 0) {
            return new SquareChargeResponse(null, "FAILED", "Invalid amount", amountInCents, currencyCode);
        }
        
        if (amountInCents > 500000) { // $5000 limit
            return new SquareChargeResponse(null, "FAILED", "Amount exceeds limit", amountInCents, currencyCode);
        }
        
        String paymentId = "sqpmt_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        System.out.println("Square API: Payment charged successfully with ID: " + paymentId);
        
        return new SquareChargeResponse(paymentId, "COMPLETED", null, amountInCents, currencyCode);
    }
    
    @Override
    public SquareVoidResponse voidPayment(String paymentId, String voidReason) {
        System.out.println("Square API: Voiding payment " + paymentId + " - Reason: " + voidReason);
        
        if (paymentId == null || paymentId.isEmpty()) {
            return new SquareVoidResponse(null, "FAILED", "Invalid payment ID");
        }
        
        String voidId = "sqvoid_" + UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        System.out.println("Square API: Payment voided successfully with ID: " + voidId);
        
        return new SquareVoidResponse(voidId, "COMPLETED", "Payment voided successfully");
    }
    
    @Override
    public boolean checkPaymentEligibility(long amountInCents, String locationId) {
        System.out.println("Square API: Checking payment eligibility for " + amountInCents + 
                         " cents at location " + locationId);
        
        boolean eligible = amountInCents > 0 && 
                          amountInCents <= 500000 && 
                          locationId != null && 
                          !locationId.isEmpty();
        
        System.out.println("Square API: Eligibility check result: " + (eligible ? "ELIGIBLE" : "NOT ELIGIBLE"));
        return eligible;
    }
}
