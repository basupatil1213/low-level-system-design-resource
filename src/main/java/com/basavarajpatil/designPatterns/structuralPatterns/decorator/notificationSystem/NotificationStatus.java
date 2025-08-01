package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Enumeration representing the various states of a notification.
 * Used to track the lifecycle and status of notification attempts.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum NotificationStatus {
    
    /**
     * Notification has been successfully sent to the recipient
     */
    SENT("Notification sent successfully"),
    
    /**
     * Notification is queued and waiting to be processed
     */
    PENDING("Notification pending processing"),
    
    /**
     * Notification failed to be sent due to an error
     */
    FAILED("Notification failed to send"),
    
    /**
     * Notification was delivered and confirmed by the recipient
     */
    DELIVERED("Notification delivered and confirmed"),
    
    /**
     * Notification send attempt was cancelled
     */
    CANCELLED("Notification sending cancelled"),
    
    /**
     * Notification is being retried after a previous failure
     */
    RETRYING("Retrying notification send");
    
    private final String description;
    
    NotificationStatus(String description) {
        this.description = description;
    }
    
    /**
     * Gets the human-readable description of this status.
     * 
     * @return The status description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this status represents a final state (no further processing needed).
     * 
     * @return true if this is a final status, false otherwise
     */
    public boolean isFinal() {
        return this == SENT || this == DELIVERED || this == FAILED || this == CANCELLED;
    }
    
    /**
     * Checks if this status represents a successful outcome.
     * 
     * @return true if this represents success, false otherwise
     */
    public boolean isSuccess() {
        return this == SENT || this == DELIVERED;
    }
    
    @Override
    public String toString() {
        return name() + ": " + description;
    }
}
