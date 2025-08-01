package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Contains metadata information for notifications including recipient details,
 * priority, scheduling, and additional context data.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class NotificationMetadata {
    
    private final String recipient;
    private final String sender;
    private final NotificationPriority priority;
    private final LocalDateTime scheduledTime;
    private final Map<String, String> additionalData;
    private final String subject;
    private final NotificationChannel preferredChannel;
    
    private NotificationMetadata(Builder builder) {
        this.recipient = builder.recipient;
        this.sender = builder.sender;
        this.priority = builder.priority != null ? builder.priority : NotificationPriority.NORMAL;
        this.scheduledTime = builder.scheduledTime;
        this.additionalData = new HashMap<>(builder.additionalData);
        this.subject = builder.subject;
        this.preferredChannel = builder.preferredChannel;
    }
    
    // Factory method for default metadata
    public static NotificationMetadata defaultMetadata() {
        return new Builder()
                .recipient("default-recipient")
                .sender("system")
                .priority(NotificationPriority.NORMAL)
                .build();
    }
    
    // Factory method for simple recipient-only metadata
    public static NotificationMetadata forRecipient(String recipient) {
        return new Builder()
                .recipient(recipient)
                .sender("system")
                .priority(NotificationPriority.NORMAL)
                .build();
    }
    
    // Factory method for urgent notifications
    public static NotificationMetadata urgent(String recipient, String subject) {
        return new Builder()
                .recipient(recipient)
                .subject(subject)
                .priority(NotificationPriority.HIGH)
                .sender("system")
                .build();
    }
    
    // Getters
    public String getRecipient() { return recipient; }
    public String getSender() { return sender; }
    public NotificationPriority getPriority() { return priority; }
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public Map<String, String> getAdditionalData() { return new HashMap<>(additionalData); }
    public String getSubject() { return subject; }
    public NotificationChannel getPreferredChannel() { return preferredChannel; }
    
    // Utility methods
    public boolean isScheduled() {
        return scheduledTime != null && scheduledTime.isAfter(LocalDateTime.now());
    }
    
    public boolean isHighPriority() {
        return priority == NotificationPriority.HIGH || priority == NotificationPriority.URGENT;
    }
    
    public String getAdditionalData(String key) {
        return additionalData.get(key);
    }
    
    // Builder pattern
    public static class Builder {
        private String recipient;
        private String sender = "system";
        private NotificationPriority priority = NotificationPriority.NORMAL;
        private LocalDateTime scheduledTime;
        private Map<String, String> additionalData = new HashMap<>();
        private String subject;
        private NotificationChannel preferredChannel;
        
        public Builder recipient(String recipient) {
            this.recipient = recipient;
            return this;
        }
        
        public Builder sender(String sender) {
            this.sender = sender;
            return this;
        }
        
        public Builder priority(NotificationPriority priority) {
            this.priority = priority;
            return this;
        }
        
        public Builder scheduledTime(LocalDateTime scheduledTime) {
            this.scheduledTime = scheduledTime;
            return this;
        }
        
        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }
        
        public Builder preferredChannel(NotificationChannel preferredChannel) {
            this.preferredChannel = preferredChannel;
            return this;
        }
        
        public Builder addData(String key, String value) {
            this.additionalData.put(key, value);
            return this;
        }
        
        public Builder addData(Map<String, String> data) {
            this.additionalData.putAll(data);
            return this;
        }
        
        public NotificationMetadata build() {
            Objects.requireNonNull(recipient, "Recipient cannot be null");
            return new NotificationMetadata(this);
        }
    }
    
    @Override
    public String toString() {
        return String.format("NotificationMetadata{recipient='%s', sender='%s', priority=%s, subject='%s', preferredChannel=%s, scheduled=%s}",
                recipient, sender, priority, subject, preferredChannel, 
                scheduledTime != null ? scheduledTime.toString() : "immediate");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationMetadata that = (NotificationMetadata) o;
        return Objects.equals(recipient, that.recipient) &&
               Objects.equals(sender, that.sender) &&
               priority == that.priority &&
               Objects.equals(subject, that.subject);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(recipient, sender, priority, subject);
    }
}
