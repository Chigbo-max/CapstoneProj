package com.antiTheftTracker.antiTheftTrackerApp.services.deviceManagementServ;

import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceDetailsRepository;
import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceDetailsResponse;
import com.antiTheftTracker.antiTheftTrackerApp.utils.androidManagement.AndroidManagementFactory;
import com.antiTheftTracker.antiTheftTrackerApp.utils.mapper.DeviceDetailsMapper;
import com.google.api.services.androidmanagement.v1.model.ApplicationReport;
import com.google.api.services.androidmanagement.v1.model.Device;
import com.google.api.services.androidmanagement.v1.model.TelephonyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DeviceDetailsServiceImpl implements DeviceDetailsService {
    private final AndroidManagementFactory androidManagementFactory;
    private final DeviceDetailsRepository deviceDetailsRepository;
    private final DeviceEntityRepository deviceEntityRepository;

    @Override
    public DeviceDetailsResponse getDeviceDetails(String deviceId) throws IOException {
        var device = fetchDeviceFromApi(deviceId);

        var deviceEntity = mapToDevice(device);

        var deviceDetails = mapToDeviceDetails(device, deviceEntity);
        deviceDetailsRepository.save(deviceDetails);


        return DeviceDetailsMapper.buildReturnResponse(deviceDetails);
    }



    private DeviceEntity mapToDevice(Device device) {
        var deviceEntity = new DeviceEntity();
        deviceEntity.setId(extractDeviceId(device));
        deviceEntity.setPolicyName(device.getPolicyName());
        deviceEntity.setEnrollmentTime(device.getEnrollmentTime());
        deviceEntity.setLastSeen(extractLastSeen(device));
        deviceEntityRepository.save(deviceEntity);
        return deviceEntity;
    }

    private DeviceDetails mapToDeviceDetails(Device device, DeviceEntity deviceEntity) {
        var deviceDetails = new DeviceDetails();
        deviceDetails.setId(extractDeviceId(device));
        deviceDetails.setManufacturer(extractManufacturer(device));
        deviceDetails.setSerialNumber(extractSerialNumber(device));
        deviceDetails.setImei(extractImei(device));
        deviceDetails.setSimIccidSlot0(extractSimIccidSlot0(device));
        deviceDetails.setSimIccidSlot1(extractSimIccidSlot1(device));
        deviceDetails.setCarrierNameSlot0(extractCarrierNameSlot0(device));
        deviceDetails.setCarrierNameSlot1(extractCarrierNameSlot1(device));
        deviceDetails.setPhoneNumberSlot0(extractPhoneNumberSlot0(device));
        deviceDetails.setPhoneNumberSlot1(extractPhoneNumberSlot1(device));
        deviceDetails.setDevice(deviceEntity);
        return deviceDetails;
    }

    private Device fetchDeviceFromApi(String deviceId) throws IOException {
        var client = androidManagementFactory.getClient();
        return client.enterprises().devices().get(deviceId).execute();
    }

    private String extractDeviceId(Device device) {
        return device.getName();
    }

    private String extractManufacturer(Device device) {
        return Optional.ofNullable(device.getHardwareInfo())
                .map(hardwareInfo -> hardwareInfo.getManufacturer())
                .orElse(null);
    }

    private String extractSerialNumber(Device device) {
        return Optional.ofNullable(device.getHardwareInfo())
                .map(hardwareInfo -> hardwareInfo.getSerialNumber())
                .orElse(null);
    }

    private String extractImei(Device device) {
        return Optional.ofNullable(device.getNetworkInfo())
                .map(networkInfo -> networkInfo.getImei())
                .orElse(null);
    }

    private String extractSimIccidSlot0(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && !telephonyInfos.isEmpty()) ? telephonyInfos.get(0).getIccId() : null;
    }

    private String extractSimIccidSlot1(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && telephonyInfos.size() > 1) ? telephonyInfos.get(1).getIccId() : null;
    }

    private String extractCarrierNameSlot0(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && !telephonyInfos.isEmpty()) ? telephonyInfos.get(0).getCarrierName() : null;
    }

    private String extractCarrierNameSlot1(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && telephonyInfos.size() > 1) ? telephonyInfos.get(1).getCarrierName() : null;
    }

    private String extractPhoneNumberSlot0(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && !telephonyInfos.isEmpty()) ? telephonyInfos.get(0).getPhoneNumber() : null;
    }

    private String extractPhoneNumberSlot1(Device device) {
        List<TelephonyInfo> telephonyInfos = device.getNetworkInfo().getTelephonyInfos();
        return (telephonyInfos != null && telephonyInfos.size() > 1) ? telephonyInfos.get(1).getPhoneNumber() : null;
    }


    private String extractLastSeen(Device device) {
        return device.getLastStatusReportTime();
    }


    private ApplicationReport getLatestApplicationReport(Device device) {
        List<ApplicationReport> reports = device.getApplicationReports();
        if (reports == null || reports.isEmpty()) return null;
        return reports.get(0);
    }


}
