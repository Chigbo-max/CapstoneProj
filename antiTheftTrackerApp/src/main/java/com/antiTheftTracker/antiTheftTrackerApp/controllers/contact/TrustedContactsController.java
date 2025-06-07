package com.antiTheftTracker.antiTheftTrackerApp.controllers.contact;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.TrustedContactRequest;
import com.antiTheftTracker.antiTheftTrackerApp.dtos.response.TrustedContactResponse;
import com.antiTheftTracker.antiTheftTrackerApp.services.contactManagement.TrustedContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trusted-contact")
@RequiredArgsConstructor
class TrustedContactsController {

    private final TrustedContactService trustedContactService;

    @PostMapping("/{deviceId}/contacts")
    public ResponseEntity<TrustedContactResponse> addTrustedContact(@PathVariable String deviceId, @RequestBody TrustedContactRequest trustedContact) {
        trustedContactService.addTrustedContact(deviceId, trustedContact);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<List<TrustedContact>> getTrustedContacts(@PathVariable String deviceId) {
        return ResponseEntity.ok(trustedContactService.getContactsForDevice(deviceId));
    }

    @DeleteMapping("/{deviceId}/{contactId}")
    public ResponseEntity<TrustedContactResponse> deleteTrustedContact(@PathVariable String deviceId, String contactId) {
        trustedContactService.deleteTrustedContact(deviceId, contactId);
        return ResponseEntity.accepted().build();
    }
}
