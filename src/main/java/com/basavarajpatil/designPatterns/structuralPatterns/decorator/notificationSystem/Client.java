package com.basavarajpatil.designPatterns.structuralPatterns.decorator.notificationSystem;

import java.time.LocalDateTime;

/**
 * Client class demonstrating the Decorator pattern for notification systems.
 * Shows how multiple notification channels can be chained together to create
 * a comprehensive notification system with various delivery methods.
 * 
 * @author Basavaraj Patil
 * @version 1.0
 */
public class Client {
    
    public static void main(String[] args) {
        System.out.println("🎨 Notification System - Decorator Pattern Demo");
        System.out.println("==================================================");
        
        // Demonstrate different decorator combinations
        demonstrateBasicNotification();
        demonstrateEmailNotification();
        demonstrateSmsNotification();
        demonstrateMultiChannelNotification();
        demonstrateComplexScenarios();
        demonstrateValidationScenarios();
        
        System.out.println("🎯 Decorator Pattern Demo Completed!");
        System.out.println("=====================================");
    }
    
    /**
     * Demonstrates basic logging notification (base component).
     */
    private static void demonstrateBasicNotification() {
        System.out.println("\n1️⃣ BASIC NOTIFICATION (Base Component)");
        System.out.println("--------------------------------------------");
        
        Notifier baseNotifier = new BaseNotifier("SecuritySystem");
        NotificationMetadata metadata = NotificationMetadata.forRecipient("admin@company.com");
        
        NotificationResult result = baseNotifier.send("System startup completed", metadata);
        System.out.println("Result: " + result);
        System.out.println("Channels: " + baseNotifier.getChannels());
    }
    
    /**
     * Demonstrates email notification decorator.
     */
    private static void demonstrateEmailNotification() {
        System.out.println("\n2️⃣ EMAIL NOTIFICATION DECORATOR");
        System.out.println("-----------------------------------");
        
        Notifier emailNotifier = new EmailNotifier(
            new BaseNotifier("PaymentSystem"),
            "smtp.company.com", 
            587, 
            "payments@company.com"
        );
        
        NotificationMetadata metadata = NotificationMetadata.urgent(
            "user@example.com", 
            "Payment Confirmation"
        );
        
        NotificationResult result = emailNotifier.send(
            "Your payment of $99.99 has been processed successfully. Transaction ID: TXN-12345", 
            metadata
        );
        
        System.out.println("Result: " + result);
        System.out.println("Channels: " + emailNotifier.getChannels());
    }
    
    /**
     * Demonstrates SMS notification decorator.
     */
    private static void demonstrateSmsNotification() {
        System.out.println("\n3️⃣ SMS NOTIFICATION DECORATOR");
        System.out.println("--------------------------------");
        
        Notifier smsNotifier = new SMSNotifier(
            new BaseNotifier("AuthSystem"),
            "Twilio",
            "api-key-123",
            "+1234567890"
        );
        
        NotificationMetadata metadata = new NotificationMetadata.Builder()
            .recipient("+1987654321")
            .priority(NotificationPriority.HIGH)
            .subject("OTP Verification")
            .sender("AuthService")
            .build();
        
        NotificationResult result = smsNotifier.send("Your OTP is: 123456. Valid for 5 minutes.", metadata);
        System.out.println("Result: " + result);
        System.out.println("Channels: " + smsNotifier.getChannels());
    }
    
    /**
     * Demonstrates multi-channel notification using decorator chaining.
     */
    private static void demonstrateMultiChannelNotification() {
        System.out.println("\n4️⃣ MULTI-CHANNEL NOTIFICATION CHAIN");
        System.out.println("--------------------------------------");
        
        // Create a comprehensive notification chain
        Notifier multiChannelNotifier = new SlackNotifier(
            new SMSNotifier(
                new EmailNotifier(
                    new BaseNotifier("CriticalAlertSystem")
                )
            )
        );
        
        NotificationMetadata metadata = new NotificationMetadata.Builder()
            .recipient("admin@company.com")  // Will be used by email
            .priority(NotificationPriority.URGENT)
            .subject("CRITICAL: Server Down")
            .sender("MonitoringSystem")
            .addData("server", "prod-web-01")
            .addData("downtime", "2 minutes")
            .build();
        
        NotificationResult result = multiChannelNotifier.send(
            "ALERT: Production server prod-web-01 is not responding. Immediate attention required!", 
            metadata
        );
        
        System.out.println("Result: " + result);
        System.out.println("Supported Channels: " + multiChannelNotifier.getChannels());
        
        // Now try with different recipients for different channels
        System.out.println("\n--- Trying with SMS recipient ---");
        NotificationMetadata smsMetadata = NotificationMetadata.forRecipient("+1555123456");
        smsMetadata = new NotificationMetadata.Builder()
            .recipient("+1555123456")
            .priority(NotificationPriority.URGENT)
            .subject("Server Alert")
            .build();
        
        NotificationResult smsResult = multiChannelNotifier.send("Server down!", smsMetadata);
        System.out.println("SMS Result: " + smsResult);
        
        System.out.println("\n--- Trying with Slack recipient ---");
        NotificationMetadata slackMetadata = NotificationMetadata.forRecipient("#alerts");
        slackMetadata = new NotificationMetadata.Builder()
            .recipient("#alerts")
            .priority(NotificationPriority.HIGH)
            .subject("Production Alert")
            .sender("DevOps")
            .build();
        
        NotificationResult slackResult = multiChannelNotifier.send("Production issue detected!", slackMetadata);
        System.out.println("Slack Result: " + slackResult);
    }
    
    /**
     * Demonstrates complex notification scenarios with scheduling and priorities.
     */
    private static void demonstrateComplexScenarios() {
        System.out.println("\n5️⃣ COMPLEX NOTIFICATION SCENARIOS");
        System.out.println("------------------------------------");
        
        // Scenario 1: Scheduled notification
        System.out.println("📅 Scheduled Notification:");
        Notifier scheduledNotifier = new EmailNotifier(new BaseNotifier("SchedulerService"));
        
        NotificationMetadata scheduledMetadata = new NotificationMetadata.Builder()
            .recipient("team@company.com")
            .priority(NotificationPriority.NORMAL)
            .subject("Weekly Report")
            .scheduledTime(LocalDateTime.now().plusHours(24))
            .sender("ReportingSystem")
            .build();
        
        NotificationResult scheduledResult = scheduledNotifier.send(
            "Your weekly performance report is ready for review.", 
            scheduledMetadata
        );
        System.out.println("Scheduled notification: " + scheduledResult);
        System.out.println("Is scheduled: " + scheduledMetadata.isScheduled());
        
        // Scenario 2: Bulk notification with different priorities
        System.out.println("\n📢 Bulk Notifications with Different Priorities:");
        
        Notifier bulkNotifier = new SMSNotifier(
            new EmailNotifier(
                new BaseNotifier("MarketingSystem")
            )
        );
        
        String[] recipients = {"user1@example.com", "+1234567890", "user3@example.com"};
        NotificationPriority[] priorities = {NotificationPriority.LOW, NotificationPriority.HIGH, NotificationPriority.NORMAL};
        String[] messages = {
            "Don't miss our weekend sale - 20% off everything!",
            "Your account security alert - please verify your identity",
            "Thank you for your recent purchase. Rate your experience!"
        };
        
        for (int i = 0; i < recipients.length; i++) {
            NotificationMetadata bulkMetadata = new NotificationMetadata.Builder()
                .recipient(recipients[i])
                .priority(priorities[i])
                .subject("Customer Communication")
                .sender("CustomerService")
                .addData("campaign", "Q4-2024")
                .build();
            
            NotificationResult bulkResult = bulkNotifier.send(messages[i], bulkMetadata);
            System.out.printf("Bulk notification %d: %s%n", i+1, bulkResult);
        }
    }
    
    /**
     * Demonstrates validation scenarios and error handling.
     */
    private static void demonstrateValidationScenarios() {
        System.out.println("\n6️⃣ VALIDATION & ERROR HANDLING");
        System.out.println("---------------------------------");
        
        Notifier validationNotifier = new SlackNotifier(
            new SMSNotifier(
                new EmailNotifier(
                    new BaseNotifier("ValidationSystem")
                )
            )
        );
        
        // Test cases with different validation outcomes
        String[][] testCases = {
            {"invalid-email", "Invalid email format test"},
            {"123-invalid-phone", "Invalid phone number test"}, 
            {"valid@email.com", "Valid email test"},
            {"+1234567890", "Valid phone test"},
            {"@validuser", "Valid Slack user test"},
            {"#validchannel", "Valid Slack channel test"},
            {"", "Empty recipient test"},
            {null, "Null recipient test"}
        };
        
        for (String[] testCase : testCases) {
            System.out.printf("\n🧪 Testing: %s%n", testCase[1]);
            
            try {
                NotificationMetadata testMetadata = testCase[0] != null ? 
                    NotificationMetadata.forRecipient(testCase[0]) : 
                    NotificationMetadata.defaultMetadata();
                
                NotificationResult testResult = validationNotifier.send(
                    "Test notification message", 
                    testMetadata
                );
                
                System.out.printf("   Validation result: %s%n", testResult.isSuccess() ? "✅ SUCCESS" : "❌ FAILED");
                System.out.printf("   Details: %s%n", testResult);
                
            } catch (Exception e) {
                System.out.printf("   Exception: %s%n", e.getMessage());
            }
        }
        
        // Test can send capabilities
        System.out.println("\n🔍 Channel Capability Testing:");
        NotificationMetadata emailMeta = NotificationMetadata.forRecipient("test@example.com");
        NotificationMetadata smsMeta = NotificationMetadata.forRecipient("+1234567890");
        NotificationMetadata slackMeta = NotificationMetadata.forRecipient("#general");
        
        System.out.printf("Can send email: %s%n", validationNotifier.canSend(emailMeta));
        System.out.printf("Can send SMS: %s%n", validationNotifier.canSend(smsMeta));
        System.out.printf("Can send Slack: %s%n", validationNotifier.canSend(slackMeta));
    }
}