package com.antiTheftTracker.antiTheftTrackerApp.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="Device Details")
public class DeviceDetails {
    @Id
    private String id;
    private String manufacturer;
    private String serialNumber;
    private String imei;
    private String simIccidSlot0;
    private String simIccidSlot1;
    private String carrierNameSlot0;
    private String carrierNameSlot1;
    private String phoneNumberSlot0;
    private String phoneNumberSlot1;
    @ManyToOne
    private DeviceEntity device;

}