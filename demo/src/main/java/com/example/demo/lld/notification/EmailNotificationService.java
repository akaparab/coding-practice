package com.example.demo.lld.notification;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending email notification " + notification.getRecipient());
    }
}
