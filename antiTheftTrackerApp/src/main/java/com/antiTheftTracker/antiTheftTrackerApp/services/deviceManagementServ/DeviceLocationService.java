package com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.DeviceLocationRequest;

public interface DeviceLocationService {
    void updateDeviceLocation(DeviceLocationRequest request);
}
