package com.antiTheftTracker.antiTheftTrackerApp.services.notificationsManagement;

import com.antiTheftTracker.antiTheftTrackerApp.services.mailAndSmsServ.EmailService;
import com.antiTheftTracker.antiTheftTrackerApp.services.mailAndSmsServ.SmsService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {

    private final SmsService smsService;
    private final EmailService emailService;
    @Override
    public void sendTheftAlert(TrustedContact contact, DeviceEntity device) throws MessagingException {
        String message = buildTheftMessage(device);

        if (contact.isSmsEnabled() && contact.getPhoneNumber() != null) {
            smsService.sendSms(contact.getPhoneNumber(), message);
        }

        if (contact.isEmailEnabled() && contact.getEmail() != null) {
            emailService.sendEmail(contact.getEmail(), "Your device was reported as stolen", message);
        }
    }

    private String buildTheftMessage(DeviceEntity device) {

        List<DeviceDetails> details = device.getDeviceDetails();
        String model = (details != null && details.size() > 2) ? String.valueOf(details.get(2)) : "N/A";
        String imei = (details != null && details.size() > 3) ? String.valueOf(details.get(3)) : "N/A";
        return String.format("""
                        ⚠️ DEVICE STOLEN ALERT ⚠️
                        
                        Device ID: %s
                        Model: %s
                        IMEI: %s
                        Last Known Location: %.5f, %.5f
                        New SIM ICCID: %s
                        Time of theft: %s
                        """,
                device.getId(),
                model,
                imei,
                device.getLatitude(),
                device.getLongitude(),
                device.getLastKnownSim(),
                device.getTheftReportTime()
        );
    }
}
