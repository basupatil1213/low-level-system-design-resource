package com.basavarajpatil.designPatterns.structuralPatterns.adapter.paymentGateway;

/**
 * External Square payment API interface.
 * This represents another third-party payment service with its own API structure
 * that's incompatible with our PaymentProcessor interface.
 * 
 * Square uses different method names and parameter structures than our standard interface.
 */
public interface SquarePaymentAPI {
    /**
     * Square's method for charging a payment method
     * @param amountInCents Amount in cents (Square works with cents, not dollars)
     * @param currencyCode ISO currency code
     * @param locationId Square location identifier
     * @param orderReference Reference number for the order
     * @return Square payment response
     */
    SquareChargeResponse chargePayment(long amountInCents, String currencyCode, 
                                      String locationId, String orderReference);
    
    /**
     * Square's method for voiding a payment
     * @param paymentId Square payment identifier
     * @param voidReason Reason for voiding the payment
     * @return Square void response
     */
    SquareVoidResponse voidPayment(String paymentId, String voidReason);
    
    /**
     * Square's method for checking payment eligibility
     * @param amountInCents Amount in cents
     * @param locationId Square location identifier
     * @return true if payment is eligible
     */
    boolean checkPaymentEligibility(long amountInCents, String locationId);
    
    /**
     * Square payment response
     */
    class SquareChargeResponse {
        private final String paymentId;
        private final String status;
        private final String errorMessage;
        private final long amountInCents;
        private final String currency;
        
        public SquareChargeResponse(String paymentId, String status, String errorMessage, 
                                   long amountInCents, String currency) {
            this.paymentId = paymentId;
            this.status = status;
            this.errorMessage = errorMessage;
            this.amountInCents = amountInCents;
            this.currency = currency;
        }
        
        public String getPaymentId() { return paymentId; }
        public String getStatus() { return status; }
        public String getErrorMessage() { return errorMessage; }
        public long getAmountInCents() { return amountInCents; }
        public String getCurrency() { return currency; }
    }
    
    /**
     * Square void response
     */
    class SquareVoidResponse {
        private final String voidId;
        private final String status;
        private final String message;
        
        public SquareVoidResponse(String voidId, String status, String message) {
            this.voidId = voidId;
            this.status = status;
            this.message = message;
        }
        
        public String getVoidId() { return voidId; }
        public String getStatus() { return status; }
        public String getMessage() { return message; }
    }
}
