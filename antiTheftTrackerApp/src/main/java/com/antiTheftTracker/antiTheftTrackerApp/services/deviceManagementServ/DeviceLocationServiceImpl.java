package com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ;

import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.DeviceLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class DeviceLocationServiceImpl implements DeviceLocationService {

    private final DeviceEntityRepository repository;
    @Override
    public void updateDeviceLocation(DeviceLocationRequest request) {
        var device = repository.findById(request.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        device.setLatitude(request.getLatitude());
        device.setLongitude(request.getLongitude());
        device.setLastSeen(LocalDateTime.now().toString());

        repository.save(device);
    }
}
