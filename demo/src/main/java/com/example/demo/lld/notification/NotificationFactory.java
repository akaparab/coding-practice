package com.example.demo.lld.notification;

public class NotificationFactory {
    public NotificationService getNotificationService(String type) {
        if (type.equals("email")) {
            return new EmailNotificationService();
        } else if (type.equals("sms")) {
            return new SMSNotificationService();
        } else if (type.equals("push")) {
            return new PushNotificationService();
        } else {
            return null;
        }
    }
}
