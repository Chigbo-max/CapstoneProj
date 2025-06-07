package com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ;

import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceEntityResponse;
import com.antiTheftTracker.antiTheftTrackerApp.utils.mapper.DeviceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeviceEntityServiceImpl implements DeviceEntityService {

    private final DeviceEntityRepository deviceEntityRepo;
    @Override
    public DeviceEntityResponse getDeviceMetaData(String deviceId) {
        DeviceEntity deviceEntity = deviceEntityRepo.findById(deviceId)
                .orElseThrow(()-> new IllegalArgumentException("Device not found"));
        return DeviceEntityMapper.buildDeviceEntityResponse(deviceEntity);

    }
}
