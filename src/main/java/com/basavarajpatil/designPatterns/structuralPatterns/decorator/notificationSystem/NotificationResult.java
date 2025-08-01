package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents the result of a notification sending operation.
 * Contains information about success/failure, message details, and metadata.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class NotificationResult {
    
    private final String id;
    private final boolean success;
    private final String message;
    private final String errorMessage;
    private final NotificationStatus status;
    private final String channel;
    private final String recipient;
    private final LocalDateTime timestamp;
    
    private NotificationResult(Builder builder) {
        this.id = builder.id;
        this.success = builder.success;
        this.message = builder.message;
        this.errorMessage = builder.errorMessage;
        this.status = builder.status;
        this.channel = builder.channel;
        this.recipient = builder.recipient;
        this.timestamp = builder.timestamp != null ? builder.timestamp : LocalDateTime.now();
    }
    
    // Factory methods for common scenarios
    public static NotificationResult success(String id, String message, String channel, String recipient) {
        return new Builder()
                .id(id)
                .success(true)
                .message(message)
                .status(NotificationStatus.SENT)
                .channel(channel)
                .recipient(recipient)
                .build();
    }
    
    public static NotificationResult failure(String message, String errorMessage, String channel) {
        return new Builder()
                .id(generateId())
                .success(false)
                .message(message)
                .errorMessage(errorMessage)
                .status(NotificationStatus.FAILED)
                .channel(channel)
                .build();
    }
    
    public static NotificationResult pending(String id, String message, String channel, String recipient) {
        return new Builder()
                .id(id)
                .success(false)
                .message(message)
                .status(NotificationStatus.PENDING)
                .channel(channel)
                .recipient(recipient)
                .build();
    }
    
    // Getters
    public String getId() { return id; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getErrorMessage() { return errorMessage; }
    public NotificationStatus getStatus() { return status; }
    public String getChannel() { return channel; }
    public String getRecipient() { return recipient; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    // Builder pattern
    public static class Builder {
        private String id;
        private boolean success;
        private String message;
        private String errorMessage;
        private NotificationStatus status;
        private String channel;
        private String recipient;
        private LocalDateTime timestamp;
        
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        
        public Builder success(boolean success) {
            this.success = success;
            return this;
        }
        
        public Builder message(String message) {
            this.message = message;
            return this;
        }
        
        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }
        
        public Builder status(NotificationStatus status) {
            this.status = status;
            return this;
        }
        
        public Builder channel(String channel) {
            this.channel = channel;
            return this;
        }
        
        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
        
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        
        public NotificationResult build() {
            if (id == null) {
                id = generateId();
            }
            return new NotificationResult(this);
        }
    }
    
    private static String generateId() {
        return "NOTIF-" + System.currentTimeMillis() + "-" + 
               Integer.toHexString((int)(Math.random() * 0xFFFF)).toUpperCase();
    }
    
    @Override
    public String toString() {
        return String.format("NotificationResult{id='%s', success=%s, status=%s, channel='%s', recipient='%s', message='%s'%s, timestamp=%s}",
                id, success, status, channel, recipient, message,
                errorMessage != null ? ", error='" + errorMessage + "'" : "",
                timestamp);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationResult that = (NotificationResult) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
