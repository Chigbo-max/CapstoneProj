package com.antiTheftTracker.antiTheftTrackerApp.service;
import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.command.CommandRepository;
import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.CommandRequest;
import com.antiTheftTracker.antiTheftTrackerApp.services.commandManagementServ.CommandServiceImpl;
import com.antiTheftTracker.antiTheftTrackerApp.services.contactManagement.TrustedContactService;
import com.antiTheftTracker.antiTheftTrackerApp.services.notificationsManagement.FirebaseMessagingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

    @ExtendWith(SpringExtension.class)
    public class CommandServiceTest {

        @InjectMocks
        private CommandServiceImpl commandService;

        @Mock
        private DeviceEntityRepository deviceRepository;

        @Mock
        private CommandRepository commandRepository;


        @Mock
        private TrustedContactService contactService;

        @Mock
        private FirebaseMessagingService firebaseMessagingService;





        @BeforeEach
        void setUp() {
            var device = new DeviceEntity();
            device.setId("device-123");
            device.setFcmToken("fcm-token-123");

            when(deviceRepository.findById("device-123")).thenReturn(Optional.of(device));
        }

        @Test
        void testIssueLockCommand_savesCommandAndSendsFCM() throws Exception {
            // Act
            commandService.issueLockCommand("device-123");

            // Assert
            verify(commandRepository).save(any(Command.class));
            verify(firebaseMessagingService).sendToDevice(
                    "fcm-token-123",
                    "SafeGad",
                    "Your device has been locked due to theft report",
                    "lock"
            );
        }

        @Test
        void testIssueUnlockCommand_savesCommandAndSendsFCM() throws Exception {
            // Act
            commandService.issueUnlockCommand("device-123");

            // Assert
            verify(commandRepository).save(any(Command.class));
            verify(firebaseMessagingService).sendToDevice(
                    "fcm-token-123",
                    "SafeGad",
                    "Your device has been unlocked",
                    "unlock"
            );
        }

        @Test
        void testIssueWipeCommand_savesToDatabaseAndSendsFCM() throws Exception {
            // Act
            commandService.issueWipeCommand("device-123");

            // Assert
            verify(commandRepository).save(any(Command.class));
            verify(firebaseMessagingService).sendToDevice(
                    "fcm-token-123",
                    "SafeGad",
                    "All data will be wiped from your device",
                    "remoteWipe"
            );
        }

        @Test
        void testIssueUnknownRemoteCommand_throwsIllegalArgumentException() {
            var request = new CommandRequest("unknown", "device-123");

            assertThatThrownBy(() -> commandService.issueRemoteCommand(request))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Unknown command type");
        }



        @Test
        void testIssueLockCommand_whenDeviceNotFound_throwsException() {
            when(deviceRepository.findById("invalid-device")).thenReturn(Optional.empty());

            assertThatThrownBy(() -> commandService.issueLockCommand("invalid-device"))
                    .isInstanceOf(RuntimeException.class);
        }

        @Test
        void testIssueWipeCommand_withoutFcmToken_doesNotSendFCM() throws Exception {
            var deviceWithoutToken = new DeviceEntity();
            deviceWithoutToken.setId("device-no-fcm");
            deviceWithoutToken.setFcmToken(null);

            when(deviceRepository.findById("device-no-fcm")).thenReturn(Optional.of(deviceWithoutToken));

            commandService.issueWipeCommand("device-no-fcm");

            verify(firebaseMessagingService, never()).sendToDevice(anyString(), anyString(), anyString(), anyString());
        }

    }
