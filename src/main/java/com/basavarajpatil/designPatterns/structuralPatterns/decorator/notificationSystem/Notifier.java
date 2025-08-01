package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Core notification interface that defines the contract for sending notifications.
 * This interface serves as the Component in the Decorator pattern, allowing
 * both concrete implementations and decorators to be used interchangeably.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public interface Notifier {
    
    /**
     * Sends a notification with the specified message and metadata.
     * 
     * @param message The notification message to be sent
     * @param metadata Additional metadata about the notification (recipient, priority, etc.)
     * @return NotificationResult containing the result of the notification attempt
     */
    NotificationResult send(String message, NotificationMetadata metadata);
    
    /**
     * Sends a simple notification with just a message using default metadata.
     * 
     * @param message The notification message to be sent
     * @return NotificationResult containing the result of the notification attempt
     */
    default NotificationResult send(String message) {
        return send(message, NotificationMetadata.defaultMetadata());
    }
    
    /**
     * Returns the channel type(s) this notifier supports.
     * For decorators, this should include all wrapped channel types.
     * 
     * @return String describing the notification channels
     */
    String getChannels();
    
    /**
     * Validates if the notifier can send notifications with the given metadata.
     * 
     * @param metadata The notification metadata to validate
     * @return true if the notification can be sent, false otherwise
     */
    boolean canSend(NotificationMetadata metadata);
}
