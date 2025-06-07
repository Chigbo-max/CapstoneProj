package com.antiTheftTracker.antiTheftTrackerApp.services.TheftReportManagement;

import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.command.CommandRepository;
import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TheftReportRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TheftReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.Double.parseDouble;

@Service
@RequiredArgsConstructor

class TheftReportServiceImpl implements TheftReportService {

    private final DeviceEntityRepository deviceRepository;
    private final CommandRepository commandRepository;

    @Override
    public TheftReportResponse handleTheftReport(TheftReportRequest request) {
        var device = deviceRepository.findById(request.getDeviceId())
                .orElseThrow(() -> new RuntimeException("Device not found"));

        device.setStolen(true);
        device.setLastKnownSim(request.getSimSerial());
        device.setLatitude(parseDouble(request.getLatitude()));
        device.setLongitude(parseDouble(request.getLongitude()));
        device.setTheftReportTime(LocalDateTime.now());

        deviceRepository.save(device);

        var command = new Command();
        setupBaseCommand(command, device, "theft_report");
        command.setSimSerial(request.getSimSerial());
        command.setLatitude(device.getLatitude());
        command.setLongitude(device.getLongitude());

        commandRepository.save(command);

        notifyTrustedContacts(request.getDeviceId(), device, command);
        TheftReportResponse response = new TheftReportResponse();
        response.setMessage("Theft report successfully sent");
        return response;

    }

    private void notifyTrustedContacts(String deviceId, DeviceEntity device, Command command) {

    }

    private void setupBaseCommand(Command command, DeviceEntity device, String commandType) {
        command.setType(commandType);
        command.setDevice(device);
        command.setIssuedAt(LocalDateTime.now());
        command.setExecuted(false);
    }





}
