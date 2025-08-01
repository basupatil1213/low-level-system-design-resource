package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

/**
 * Abstract base class for all notification decorators.
 * This class implements the Decorator pattern by maintaining a reference
 * to a Notifier component and delegating basic operations to it.
 * Concrete decorators extend this class to add specific functionality.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public abstract class NotifierDecorator implements Notifier {
    
    protected final Notifier wrappedNotifier;
    
    /**
     * Creates a new notifier decorator wrapping the specified notifier.
     * 
     * @param notifier The notifier to wrap and enhance
     * @throws IllegalArgumentException if notifier is null
     */
    public NotifierDecorator(Notifier notifier) {
        if (notifier == null) {
            throw new IllegalArgumentException("Wrapped notifier cannot be null");
        }
        this.wrappedNotifier = notifier;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        // Default implementation delegates to wrapped notifier
        // Concrete decorators can override this to add behavior before/after
        return wrappedNotifier.send(message, metadata);
    }
    
    @Override
    public String getChannels() {
        // Default implementation delegates to wrapped notifier
        // Concrete decorators should override to include their channel
        return wrappedNotifier.getChannels();
    }
    
    @Override
    public boolean canSend(NotificationMetadata metadata) {
        // Default implementation delegates to wrapped notifier
        // Concrete decorators can add additional validation
        return wrappedNotifier.canSend(metadata);
    }
    
    /**
     * Gets the wrapped notifier instance.
     * 
     * @return The wrapped notifier
     */
    protected Notifier getWrappedNotifier() {
        return wrappedNotifier;
    }
    
    /**
     * Validates if this decorator can handle the notification.
     * Concrete decorators should override this method to add
     * channel-specific validation logic.
     * 
     * @param metadata The notification metadata to validate
     * @return true if this decorator can handle the notification
     */
    protected abstract boolean canHandleNotification(NotificationMetadata metadata);
    
    /**
     * Performs the actual notification sending for this decorator.
     * Concrete decorators must implement this method to define
     * their specific notification behavior.
     * 
     * @param message The message to send
     * @param metadata The notification metadata
     * @return The result of the notification attempt
     */
    protected abstract NotificationResult performNotification(String message, NotificationMetadata metadata);
    
    /**
     * Gets the channel identifier for this decorator.
     * 
     * @return The channel identifier
     */
    protected abstract String getChannelIdentifier();
    
    @Override
    public String toString() {
        return String.format("%s{wrapping=%s}", 
                getClass().getSimpleName(), wrappedNotifier.toString());
    }
}
