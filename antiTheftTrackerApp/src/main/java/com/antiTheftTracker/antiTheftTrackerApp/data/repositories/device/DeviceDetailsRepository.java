package com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceDetailsRepository extends JpaRepository<DeviceDetails, String> {
    Optional<DeviceDetails> findByImei(String imei);
    Optional<DeviceDetails> findBySerialNumber(String serialNumber);

}
