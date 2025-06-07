package com.antiTheftTracker.antiTheftTrackerApp.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceRegistrationRequest {
    private boolean oneTimeOnly;
}
