package com.antiTheftTracker.antiTheftTrackerApp.controllers;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceDetailsResponse;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceEntityResponse;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceRegistrationResponse;
import com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ.DeviceDetailsService;
import com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ.DeviceEnrollmentService;
import com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ.DeviceEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    @Autowired
    DeviceEnrollmentService deviceEnrollmentService;
    @Autowired
    DeviceEntityService deviceEntityService;
    @Autowired
    DeviceDetailsService deviceDetailsService;

    @GetMapping("/enrol")
    public DeviceRegistrationResponse generateEnrollmentLink()throws IOException {

        return deviceEnrollmentService.generateEnrollmentLink();
    }

    @GetMapping("/meta-data/{deviceId}")
    public ResponseEntity<DeviceEntityResponse> getDeviceMetaData(@PathVariable String deviceId){
        DeviceEntityResponse response =  deviceEntityService.getDeviceMetaData(deviceId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/details/{deviceId}")
    public ResponseEntity<DeviceDetailsResponse>  getDeviceDetails(@PathVariable String deviceId)throws IOException{
       DeviceDetailsResponse response = deviceDetailsService.getDeviceDetails(deviceId);
       return ResponseEntity.ok(response);
    }

}