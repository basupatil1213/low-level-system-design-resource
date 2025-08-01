package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.util.regex.Pattern;

/**
 * SMS notification decorator that adds SMS sending capabilities
 * to any existing notifier. This decorator validates phone numbers
 * and simulates SMS sending functionality.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class SMSNotifier extends NotifierDecorator {
    
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^\\+?[1-9]\\d{1,14}$"); // E.164 format
    
    private final String apiKey;
    private final String senderNumber;
    private final String provider;
    
    /**
     * Creates an SMS notifier with default configuration.
     * 
     * @param notifier The notifier to decorate
     */
    public SMSNotifier(Notifier notifier) {
        this(notifier, "twilio", "default-api-key", "+1234567890");
    }
    
    /**
     * Creates an SMS notifier with custom configuration.
     * 
     * @param notifier The notifier to decorate
     * @param provider The SMS service provider name
     * @param apiKey The API key for the SMS service
     * @param senderNumber The sender phone number
     */
    public SMSNotifier(Notifier notifier, String provider, String apiKey, String senderNumber) {
        super(notifier);
        this.provider = provider;
        this.apiKey = apiKey;
        this.senderNumber = senderNumber;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        try {
            // First validate if we can send SMS
            if (!canHandleNotification(metadata)) {
                System.err.printf("SMSNotifier: Cannot send SMS to %s - validation failed%n", 
                        metadata.getRecipient());
                // Still delegate to wrapped notifier
                return wrappedNotifier.send(message, metadata);
            }
            
            // Perform SMS notification
            NotificationResult smsResult = performNotification(message, metadata);
            
            // Always delegate to wrapped notifier (decorator chain)
            NotificationResult wrappedResult = wrappedNotifier.send(message, metadata);
            
            // Return the SMS result (most specific result)
            return smsResult.isSuccess() ? smsResult : wrappedResult;
            
        } catch (Exception e) {
            System.err.printf("SMSNotifier: Unexpected error - %s%n", e.getMessage());
            return NotificationResult.failure(
                message, 
                "SMS notification failed: " + e.getMessage(), 
                getChannelIdentifier()
            );
        }
    }
    
    @Override
    protected NotificationResult performNotification(String message, NotificationMetadata metadata) {
        try {
            String recipient = metadata.getRecipient();
            
            // Truncate message for SMS limits (160 characters for single SMS)
            String smsMessage = message.length() > 160 ? 
                message.substring(0, 157) + "..." : message;
            
            // Simulate SMS sending
            System.out.printf("📱 SMS Notification:%n");
            System.out.printf("   Provider: %s%n", provider);
            System.out.printf("   From: %s%n", senderNumber);
            System.out.printf("   To: %s%n", recipient);
            System.out.printf("   Message: %s%n", smsMessage);
            System.out.printf("   Length: %d characters%n", smsMessage.length());
            System.out.printf("   Priority: %s%n", metadata.getPriority());
            
            // Simulate processing time based on priority
            int delay = metadata.getPriority().isImmediate() ? 50 : 150;
            Thread.sleep(delay);
            
            // Simulate occasional delivery delays for normal priority
            if (metadata.getPriority() == NotificationPriority.NORMAL && Math.random() < 0.1) {
                System.out.printf("   Status: ⏳ SMS queued for delivery%n%n");
                return NotificationResult.pending(
                    generateSmsId(), 
                    smsMessage, 
                    getChannelIdentifier(), 
                    recipient
                );
            }
            
            System.out.printf("   Status: ✅ SMS sent successfully%n%n");
            
            return NotificationResult.success(
                generateSmsId(), 
                smsMessage, 
                getChannelIdentifier(), 
                recipient
            );
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NotificationResult.failure(
                message, 
                "SMS sending interrupted", 
                getChannelIdentifier()
            );
        } catch (Exception e) {
            return NotificationResult.failure(
                message, 
                "SMS sending failed: " + e.getMessage(), 
                getChannelIdentifier()
            );
        }
    }
    
    @Override
    protected boolean canHandleNotification(NotificationMetadata metadata) {
        if (metadata == null || metadata.getRecipient() == null) {
            return false;
        }
        
        String recipient = metadata.getRecipient().trim();
        if (recipient.isEmpty()) {
            return false;
        }
        
        // Validate phone number format
        if (!PHONE_PATTERN.matcher(recipient).matches()) {
            System.err.printf("SMSNotifier: Invalid phone number format: %s%n", recipient);
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getChannels() {
        return getChannelIdentifier() + " + " + wrappedNotifier.getChannels();
    }
    
    @Override
    protected String getChannelIdentifier() {
        return "SMS";
    }
    
    @Override
    public boolean canSend(NotificationMetadata metadata) {
        return canHandleNotification(metadata) || wrappedNotifier.canSend(metadata);
    }
    
    /**
     * Generates a unique SMS notification ID.
     * 
     * @return A unique SMS notification identifier
     */
    private String generateSmsId() {
        return "SMS-" + System.currentTimeMillis() + "-" + 
               Integer.toHexString((int)(Math.random() * 0xFFFF)).toUpperCase();
    }
    
    /**
     * Gets the SMS service provider name.
     * 
     * @return The provider name
     */
    public String getProvider() {
        return provider;
    }
    
    /**
     * Gets the sender phone number.
     * 
     * @return The sender phone number
     */
    public String getSenderNumber() {
        return senderNumber;
    }
    
    /**
     * Checks if a message requires multiple SMS parts.
     * 
     * @param message The message to check
     * @return true if the message requires multiple SMS parts
     */
    public boolean requiresMultipleParts(String message) {
        return message != null && message.length() > 160;
    }
    
    /**
     * Calculates the number of SMS parts needed for a message.
     * 
     * @param message The message to analyze
     * @return The number of SMS parts required
     */
    public int calculateSmsParts(String message) {
        if (message == null || message.isEmpty()) {
            return 0;
        }
        return (int) Math.ceil(message.length() / 160.0);
    }
}
