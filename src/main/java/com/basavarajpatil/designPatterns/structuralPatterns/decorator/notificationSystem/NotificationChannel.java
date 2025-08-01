package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Enumeration representing different notification channels/mediums.
 * Used to specify preferred delivery methods for notifications.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum NotificationChannel {
    
    /**
     * Email notification channel
     */
    EMAIL("Email", "email", true),
    
    /**
     * SMS text message channel
     */
    SMS("SMS", "sms", true),
    
    /**
     * Push notification channel (mobile/web)
     */
    PUSH("Push Notification", "push", false),
    
    /**
     * Slack messaging channel
     */
    SLACK("Slack", "slack", false),
    
    /**
     * In-app notification channel
     */
    IN_APP("In-App", "in-app", false),
    
    /**
     * Voice call channel
     */
    VOICE("Voice Call", "voice", true),
    
    /**
     * WebHook callback channel
     */
    WEBHOOK("WebHook", "webhook", false);
    
    private final String displayName;
    private final String identifier;
    private final boolean requiresRecipient;
    
    NotificationChannel(String displayName, String identifier, boolean requiresRecipient) {
        this.displayName = displayName;
        this.identifier = identifier;
        this.requiresRecipient = requiresRecipient;
    }
    
    /**
     * Gets the human-readable display name for this channel.
     * 
     * @return The display name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Gets the system identifier for this channel.
     * 
     * @return The channel identifier
     */
    public String getIdentifier() {
        return identifier;
    }
    
    /**
     * Checks if this channel requires recipient information.
     * 
     * @return true if recipient is required, false otherwise
     */
    public boolean requiresRecipient() {
        return requiresRecipient;
    }
    
    /**
     * Checks if this channel supports immediate delivery.
     * 
     * @return true if immediate delivery is supported, false otherwise
     */
    public boolean supportsImmediateDelivery() {
        return this == SMS || this == EMAIL || this == PUSH || this == VOICE;
    }
    
    /**
     * Finds a notification channel by its identifier.
     * 
     * @param identifier The channel identifier to search for
     * @return The matching NotificationChannel, or null if not found
     */
    public static NotificationChannel fromIdentifier(String identifier) {
        for (NotificationChannel channel : values()) {
            if (channel.identifier.equalsIgnoreCase(identifier)) {
                return channel;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return displayName + " (" + identifier + ")";
    }
}
