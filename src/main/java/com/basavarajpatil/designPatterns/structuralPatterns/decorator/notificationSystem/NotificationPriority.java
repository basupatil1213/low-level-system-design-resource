package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Enumeration representing different priority levels for notifications.
 * Used to determine urgency and processing order of notifications.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public enum NotificationPriority {
    
    /**
     * Low priority notifications - can be delayed or batched
     */
    LOW(1, "Low Priority"),
    
    /**
     * Normal priority notifications - standard processing
     */
    NORMAL(2, "Normal Priority"),
    
    /**
     * High priority notifications - expedited processing
     */
    HIGH(3, "High Priority"),
    
    /**
     * Urgent notifications - immediate processing required
     */
    URGENT(4, "Urgent Priority");
    
    private final int level;
    private final String description;
    
    NotificationPriority(int level, String description) {
        this.level = level;
        this.description = description;
    }
    
    /**
     * Gets the numeric priority level (higher number = higher priority).
     * 
     * @return The priority level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Gets the human-readable description of this priority.
     * 
     * @return The priority description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Checks if this priority is higher than the specified priority.
     * 
     * @param other The priority to compare against
     * @return true if this priority is higher, false otherwise
     */
    public boolean isHigherThan(NotificationPriority other) {
        return this.level > other.level;
    }
    
    /**
     * Checks if this priority requires immediate processing.
     * 
     * @return true if immediate processing is required, false otherwise
     */
    public boolean isImmediate() {
        return this == HIGH || this == URGENT;
    }
    
    @Override
    public String toString() {
        return description + " (" + level + ")";
    }
}
