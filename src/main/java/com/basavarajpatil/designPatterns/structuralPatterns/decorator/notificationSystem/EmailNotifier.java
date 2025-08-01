package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.util.regex.Pattern;

/**
 * Email notification decorator that adds email sending capabilities
 * to any existing notifier. This decorator validates email addresses
 * and simulates email sending functionality.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class EmailNotifier extends NotifierDecorator {
    
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    private final String smtpServer;
    private final int port;
    private final String senderEmail;
    
    /**
     * Creates an email notifier with default SMTP configuration.
     * 
     * @param notifier The notifier to decorate
     */
    public EmailNotifier(Notifier notifier) {
        this(notifier, "smtp.gmail.com", 587, "noreply@system.com");
    }
    
    /**
     * Creates an email notifier with custom SMTP configuration.
     * 
     * @param notifier The notifier to decorate
     * @param smtpServer The SMTP server address
     * @param port The SMTP port
     * @param senderEmail The sender email address
     */
    public EmailNotifier(Notifier notifier, String smtpServer, int port, String senderEmail) {
        super(notifier);
        this.smtpServer = smtpServer;
        this.port = port;
        this.senderEmail = senderEmail;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        try {
            // First validate if we can send email
            if (!canHandleNotification(metadata)) {
                System.err.printf("EmailNotifier: Cannot send email to %s - validation failed%n", 
                        metadata.getRecipient());
                // Still delegate to wrapped notifier
                return wrappedNotifier.send(message, metadata);
            }
            
            // Perform email notification
            NotificationResult emailResult = performNotification(message, metadata);
            
            // Always delegate to wrapped notifier (decorator chain)
            NotificationResult wrappedResult = wrappedNotifier.send(message, metadata);
            
            // Return the email result (most specific result)
            return emailResult.isSuccess() ? emailResult : wrappedResult;
            
        } catch (Exception e) {
            System.err.printf("EmailNotifier: Unexpected error - %s%n", e.getMessage());
            return NotificationResult.failure(
                message, 
                "Email notification failed: " + e.getMessage(), 
                getChannelIdentifier()
            );
        }
    }
    
    @Override
    protected NotificationResult performNotification(String message, NotificationMetadata metadata) {
        try {
            String recipient = metadata.getRecipient();
            String subject = metadata.getSubject() != null ? metadata.getSubject() : "Notification";
            
            // Simulate email sending
            System.out.printf("📧 Email Notification:%n");
            System.out.printf("   Server: %s:%d%n", smtpServer, port);
            System.out.printf("   From: %s%n", senderEmail);
            System.out.printf("   To: %s%n", recipient);
            System.out.printf("   Subject: %s%n", subject);
            System.out.printf("   Message: %s%n", message);
            System.out.printf("   Priority: %s%n", metadata.getPriority());
            
            // Simulate processing time based on priority
            int delay = metadata.getPriority().isImmediate() ? 100 : 200;
            Thread.sleep(delay);
            
            System.out.printf("   Status: ✅ Email sent successfully%n%n");
            
            return NotificationResult.success(
                generateEmailId(), 
                message, 
                getChannelIdentifier(), 
                recipient
            );
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NotificationResult.failure(
                message, 
                "Email sending interrupted", 
                getChannelIdentifier()
            );
        } catch (Exception e) {
            return NotificationResult.failure(
                message, 
                "Email sending failed: " + e.getMessage(), 
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
        
        // Validate email format
        if (!EMAIL_PATTERN.matcher(recipient).matches()) {
            System.err.printf("EmailNotifier: Invalid email format: %s%n", recipient);
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
        return "EMAIL";
    }
    
    @Override
    public boolean canSend(NotificationMetadata metadata) {
        return canHandleNotification(metadata) || wrappedNotifier.canSend(metadata);
    }
    
    /**
     * Generates a unique email notification ID.
     * 
     * @return A unique email notification identifier
     */
    private String generateEmailId() {
        return "EMAIL-" + System.currentTimeMillis() + "-" + 
               Integer.toHexString((int)(Math.random() * 0xFFFF)).toUpperCase();
    }
    
    /**
     * Gets the SMTP server configuration.
     * 
     * @return The SMTP server address
     */
    public String getSmtpServer() {
        return smtpServer;
    }
    
    /**
     * Gets the SMTP port configuration.
     * 
     * @return The SMTP port
     */
    public int getPort() {
        return port;
    }
    
    /**
     * Gets the sender email address.
     * 
     * @return The sender email address
     */
    public String getSenderEmail() {
        return senderEmail;
    }
}
