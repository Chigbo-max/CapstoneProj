package com.antiTheftTracker.antiTheftTrackerApp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DeviceEntityResponse {
    private String id;
    private String policyName;
    private String enrollmentTime;
    private double longitude;
    private double latitude;
    private String lastSeen;
}
