package com.antiTheftTracker.antiTheftTrackerApp.services.notificationsManagement;

import jakarta.mail.MessagingException;

public interface NotificationService {
    void sendTheftAlert(TrustedContact contact, DeviceEntity device) throws MessagingException;
}
