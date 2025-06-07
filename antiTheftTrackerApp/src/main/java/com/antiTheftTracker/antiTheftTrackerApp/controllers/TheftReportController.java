package com.antiTheftTracker.antiTheftTrackerApp.controllers;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TheftReportRequest;
import com.antiTheftTracker.antiTheftTrackerApp.services.TheftReportManagement.TheftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/theft")
@RequiredArgsConstructor
class TheftReportController {

    private final TheftReportService theftReportService;

    @PostMapping("/report/{deviceId}")
    ResponseEntity<Void> reportTheft(@PathVariable String deviceId){
        TheftReportRequest request = TheftReportRequest.builder()
                .deviceId(deviceId)
                .build();
        theftReportService.handleTheftReport(request);
        return ResponseEntity.ok().build();

    }
}
