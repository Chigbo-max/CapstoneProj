package com.antiTheftTracker.antiTheftTrackerApp.services.TheftReportManagement;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TheftReportRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TheftReportResponse;

public interface TheftReportService {
    TheftReportResponse handleTheftReport(TheftReportRequest theftReportRequest);


}
