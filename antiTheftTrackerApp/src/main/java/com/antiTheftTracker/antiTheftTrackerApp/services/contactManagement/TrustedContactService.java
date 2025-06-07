package com.antiTheftTracker.antiTheftTrackerApp.services.contactManagement;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TrustedContactRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TrustedContactResponse;

import java.util.List;

public interface TrustedContactService {

    List<TrustedContact> getContactsForDevice(String id);
    TrustedContactResponse addTrustedContact(String deviceId, TrustedContactRequest request);
    TrustedContactResponse deleteTrustedContact(String deviceId, String contactId);
}
