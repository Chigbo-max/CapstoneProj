package com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceEntityRepository extends JpaRepository<DeviceEntity, String> {
    List<DeviceEntity> findByUserId(String userId);

}
