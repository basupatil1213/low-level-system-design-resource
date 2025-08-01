package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Base concrete implementation of the Notifier interface.
 * This serves as the core component in the Decorator pattern,
 * providing basic logging functionality that can be decorated
 * with additional notification capabilities.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class BaseNotifier implements Notifier {
    
    private final String systemName;
    
    /**
     * Creates a base notifier with default system name.
     */
    public BaseNotifier() {
        this("NotificationSystem");
    }
    
    /**
     * Creates a base notifier with custom system name.
     * 
     * @param systemName The name of the system sending notifications
     */
    public BaseNotifier(String systemName) {
        this.systemName = systemName;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        try {
            // Validate inputs
            if (!canSend(metadata)) {
                return NotificationResult.failure(
                    message, 
                    "Validation failed for base notification", 
                    getChannels()
                );
            }
            
            // Log the notification (base functionality)
            String logMessage = String.format("[%s] Logging notification for %s: %s", 
                    systemName, metadata.getRecipient(), message);
            System.out.println(logMessage);
            
            // Simulate processing time
            Thread.sleep(50);
            
            return NotificationResult.success(
                generateNotificationId(), 
                message, 
                getChannels(), 
                metadata.getRecipient()
            );
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NotificationResult.failure(
                message, 
                "Processing interrupted: " + e.getMessage(), 
                getChannels()
            );
        } catch (Exception e) {
            return NotificationResult.failure(
                message, 
                "Unexpected error in base notifier: " + e.getMessage(), 
                getChannels()
            );
        }
    }
    
    @Override
    public String getChannels() {
        return "LOG";
    }
    
    @Override
    public boolean canSend(NotificationMetadata metadata) {
        // Base notifier can always log, but validate basic requirements
        if (metadata == null) {
            System.err.println("BaseNotifier: Metadata cannot be null");
            return false;
        }
        
        if (metadata.getRecipient() == null || metadata.getRecipient().trim().isEmpty()) {
            System.err.println("BaseNotifier: Recipient cannot be null or empty");
            return false;
        }
        
        return true;
    }
    
    /**
     * Generates a unique notification ID for tracking purposes.
     * 
     * @return A unique notification identifier
     */
    protected String generateNotificationId() {
        return "BASE-" + System.currentTimeMillis() + "-" + 
               Integer.toHexString((int)(Math.random() * 0xFFFF)).toUpperCase();
    }
    
    /**
     * Gets the system name for this base notifier.
     * 
     * @return The system name
     */
    public String getSystemName() {
        return systemName;
    }
    
    @Override
    public String toString() {
        return String.format("BaseNotifier{systemName='%s', channels='%s'}", 
                systemName, getChannels());
    }
}
