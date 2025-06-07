package com.antiTheftTracker.antiTheftTrackerApp.utils.mapper;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.DeviceDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class DeviceDetailsMapper {

    public static DeviceDetailsResponse buildReturnResponse(DeviceDetails deviceDetails) {
        return new DeviceDetailsResponse(
                deviceDetails.getId(),
                deviceDetails.getManufacturer(),
                deviceDetails.getSerialNumber(),
                deviceDetails.getImei(),
                deviceDetails.getSimIccidSlot0(),
                deviceDetails.getSimIccidSlot1(),
                deviceDetails.getCarrierNameSlot1(),
                deviceDetails.getCarrierNameSlot0(),
                deviceDetails.getPhoneNumberSlot0(),
                deviceDetails.getPhoneNumberSlot1()


        );
    }
}
