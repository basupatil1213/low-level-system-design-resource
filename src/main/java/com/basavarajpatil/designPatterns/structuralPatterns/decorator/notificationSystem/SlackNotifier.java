package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.util.regex.Pattern;

/**
 * Slack notification decorator that adds Slack messaging capabilities
 * to any existing notifier. This decorator validates Slack usernames/channels
 * and simulates Slack message sending functionality.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class SlackNotifier extends NotifierDecorator {
    
    private static final Pattern SLACK_USER_PATTERN = 
        Pattern.compile("^@[a-zA-Z0-9._-]+$");
    private static final Pattern SLACK_CHANNEL_PATTERN = 
        Pattern.compile("^#[a-zA-Z0-9_-]+$");
    
    private final String workspaceUrl;
    private final String botToken;
    private final String botName;
    
    /**
     * Creates a Slack notifier with default configuration.
     * 
     * @param notifier The notifier to decorate
     */
    public SlackNotifier(Notifier notifier) {
        this(notifier, "https://workspace.slack.com", "bot-token", "NotificationBot");
    }
    
    /**
     * Creates a Slack notifier with custom configuration.
     * 
     * @param notifier The notifier to decorate
     * @param workspaceUrl The Slack workspace URL
     * @param botToken The bot authentication token
     * @param botName The bot display name
     */
    public SlackNotifier(Notifier notifier, String workspaceUrl, String botToken, String botName) {
        super(notifier);
        this.workspaceUrl = workspaceUrl;
        this.botToken = botToken;
        this.botName = botName;
    }
    
    @Override
    public NotificationResult send(String message, NotificationMetadata metadata) {
        try {
            // First validate if we can send Slack message
            if (!canHandleNotification(metadata)) {
                System.err.printf("SlackNotifier: Cannot send Slack message to %s - validation failed%n", 
                        metadata.getRecipient());
                // Still delegate to wrapped notifier
                return wrappedNotifier.send(message, metadata);
            }
            
            // Perform Slack notification
            NotificationResult slackResult = performNotification(message, metadata);
            
            // Always delegate to wrapped notifier (decorator chain)
            NotificationResult wrappedResult = wrappedNotifier.send(message, metadata);
            
            // Return the Slack result (most specific result)
            return slackResult.isSuccess() ? slackResult : wrappedResult;
            
        } catch (Exception e) {
            System.err.printf("SlackNotifier: Unexpected error - %s%n", e.getMessage());
            return NotificationResult.failure(
                message, 
                "Slack notification failed: " + e.getMessage(), 
                getChannelIdentifier()
            );
        }
    }
    
    @Override
    protected NotificationResult performNotification(String message, NotificationMetadata metadata) {
        try {
            String recipient = metadata.getRecipient();
            String messageType = isChannel(recipient) ? "Channel" : "Direct Message";
            
            // Format message with Slack markdown
            String formattedMessage = formatSlackMessage(message, metadata);
            
            // Simulate Slack API call
            System.out.printf("💬 Slack Notification:%n");
            System.out.printf("   Workspace: %s%n", workspaceUrl);
            System.out.printf("   Bot: %s%n", botName);
            System.out.printf("   Type: %s%n", messageType);
            System.out.printf("   Target: %s%n", recipient);
            System.out.printf("   Message: %s%n", formattedMessage);
            System.out.printf("   Priority: %s%n", metadata.getPriority());
            
            // Simulate processing time
            Thread.sleep(75);
            
            // Simulate occasional rate limiting
            if (Math.random() < 0.05) {
                System.out.printf("   Status: ⚠️ Rate limited, retrying...%n");
                Thread.sleep(100);
            }
            
            System.out.printf("   Status: ✅ Slack message sent successfully%n%n");
            
            return NotificationResult.success(
                generateSlackId(), 
                formattedMessage, 
                getChannelIdentifier(), 
                recipient
            );
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return NotificationResult.failure(
                message, 
                "Slack sending interrupted", 
                getChannelIdentifier()
            );
        } catch (Exception e) {
            return NotificationResult.failure(
                message, 
                "Slack sending failed: " + e.getMessage(), 
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
        
        // Validate Slack user or channel format
        boolean isValidUser = SLACK_USER_PATTERN.matcher(recipient).matches();
        boolean isValidChannel = SLACK_CHANNEL_PATTERN.matcher(recipient).matches();
        
        if (!isValidUser && !isValidChannel) {
            System.err.printf("SlackNotifier: Invalid Slack format: %s (should be @user or #channel)%n", recipient);
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
        return "SLACK";
    }
    
    @Override
    public boolean canSend(NotificationMetadata metadata) {
        return canHandleNotification(metadata) || wrappedNotifier.canSend(metadata);
    }
    
    /**
     * Formats a message for Slack with appropriate markdown and priority indicators.
     * 
     * @param message The original message
     * @param metadata The notification metadata
     * @return The formatted Slack message
     */
    private String formatSlackMessage(String message, NotificationMetadata metadata) {
        StringBuilder formatted = new StringBuilder();
        
        // Add priority indicator
        switch (metadata.getPriority()) {
            case URGENT:
                formatted.append("🚨 *URGENT* 🚨\n");
                break;
            case HIGH:
                formatted.append("⚡ *HIGH PRIORITY* ⚡\n");
                break;
            case LOW:
                formatted.append("ℹ️ ");
                break;
            default:
                break;
        }
        
        // Add subject if available
        if (metadata.getSubject() != null && !metadata.getSubject().isEmpty()) {
            formatted.append("*").append(metadata.getSubject()).append("*\n");
        }
        
        // Add main message
        formatted.append(message);
        
        // Add sender info
        if (metadata.getSender() != null && !metadata.getSender().equals("system")) {
            formatted.append("\n_Sent by: ").append(metadata.getSender()).append("_");
        }
        
        return formatted.toString();
    }
    
    /**
     * Checks if the recipient is a Slack channel.
     * 
     * @param recipient The recipient string
     * @return true if it's a channel (#channel), false if it's a user (@user)
     */
    private boolean isChannel(String recipient) {
        return recipient.startsWith("#");
    }
    
    /**
     * Generates a unique Slack notification ID.
     * 
     * @return A unique Slack notification identifier
     */
    private String generateSlackId() {
        return "SLACK-" + System.currentTimeMillis() + "-" + 
               Integer.toHexString((int)(Math.random() * 0xFFFF)).toUpperCase();
    }
    
    /**
     * Gets the Slack workspace URL.
     * 
     * @return The workspace URL
     */
    public String getWorkspaceUrl() {
        return workspaceUrl;
    }
    
    /**
     * Gets the bot name.
     * 
     * @return The bot display name
     */
    public String getBotName() {
        return botName;
    }
}
