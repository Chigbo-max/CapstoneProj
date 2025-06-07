package com.antiTheftTracker.antiTheftTrackerApp.services.contactManagement;

import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.contact.TrustedContactRepository;
import com.antiTheftTracker.antiTheftTrackerApp.data.repositories.device.DeviceEntityRepository;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TrustedContactRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TrustedContactResponse;
import com.antiTheftTracker.antiTheftTrackerApp.exceptions.DeviceNotFoundException;
import com.antiTheftTracker.antiTheftTrackerApp.exceptions.IllegalTrustedContactException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

class TrustedContactServiceImpl implements TrustedContactService {

    private final TrustedContactRepository trustedContactRepository;
    private final ModelMapper modelMapper;
    private final DeviceEntityRepository deviceEntityRepository;

    @Override
    public List<TrustedContact> getContactsForDevice(String id) {
        return trustedContactRepository.findByDeviceId(id);
    }

    @Override
    public TrustedContactResponse addTrustedContact(String deviceId, TrustedContactRequest request) {

        if (!deviceEntityRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException("Device not found. Please enroll a device first.");
        }

        if (isNullOrEmpty(request.getName()) ||
                isNullOrEmpty(request.getPhoneNumber()) ||
                isNullOrEmpty(request.getEmail())) {
            throw new IllegalArgumentException("All fields (name, phone number, email) are required");
        }



        List<TrustedContact> existingContacts = trustedContactRepository.findByDeviceId(deviceId);

        boolean alreadyExists = existingContacts.stream().anyMatch(contact ->
                contact.getPhoneNumber().equalsIgnoreCase(request.getPhoneNumber()) ||
                        contact.getEmail().equalsIgnoreCase(request.getEmail())
        );
        if (alreadyExists) {
            throw new IllegalArgumentException("This contact already exists for this device");
        }

        if (existingContacts.size() >= 3) {
            throw new IllegalTrustedContactException("Sorry, you can't add more than 3 contacts");
        }


        DeviceEntity device = deviceEntityRepository.findById(deviceId).orElseThrow();

        TrustedContact contact = modelMapper.map(request, TrustedContact.class);

        contact.setDevice(device);


        trustedContactRepository.save(contact);

        TrustedContactResponse response = new TrustedContactResponse();
        response.setMessage("Successfully added contact");
        return response;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    @Override
    public TrustedContactResponse deleteTrustedContact(String deviceId, String contactId) {
        if(!deviceEntityRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException("Device not found. Please enroll a device first.");
        }
        Optional<TrustedContact> existingContacts = trustedContactRepository.findById(contactId);
        if (existingContacts.isEmpty()) {
            throw new IllegalTrustedContactException("Sorry, you can't delete a contact");
        }
        trustedContactRepository.deleteById(contactId);
        TrustedContactResponse response = new TrustedContactResponse();
        response.setMessage("Successfully deleted contact");
        return response;
    }
}
