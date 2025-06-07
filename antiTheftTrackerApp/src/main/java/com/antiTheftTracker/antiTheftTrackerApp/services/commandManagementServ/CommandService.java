package com.antiTheftTracker.antiTheftTrackerApp.services.commandManagementServ;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.CommandRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TheftReportRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TheftReportResponse;

public interface CommandService {
    void issueLockCommand(String deviceId);
    void issueUnlockCommand(String deviceId);
    void issueWipeCommand(String deviceId);

    void issueRemoteCommand(CommandRequest request);

    }
