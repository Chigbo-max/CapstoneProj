package com.antiTheftTracker.antiTheftTrackerApp.utils.mapper;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceEntityResponse;
import org.springframework.stereotype.Component;

@Component
public class DeviceEntityMapper {

    public static DeviceEntityResponse buildDeviceEntityResponse(DeviceEntity entity) {
        return new DeviceEntityResponse(
                entity.getId(),
                entity.getPolicyName(),
                entity.getEnrollmentTime(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getLastSeen()
        );
    }
}
