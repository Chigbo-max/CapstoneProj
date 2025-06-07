package com.antiTheftTracker.antiTheftTrackerApp.controllers;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.DeviceLocationRequest;
import com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ.DeviceLocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/device/location")
public class DeviceLocationController {

    private final DeviceLocationService locationService;

    @PostMapping
    public ResponseEntity<Void> updateDeviceLocation(@RequestBody DeviceLocationRequest request) {
        locationService.updateDeviceLocation(request);
        return ResponseEntity.accepted().build();
    }
}
