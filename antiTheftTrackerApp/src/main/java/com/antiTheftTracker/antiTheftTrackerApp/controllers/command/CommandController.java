package com.antiTheftTracker.antiTheftTrackerApp.controllers.command;

import com.antiTheftTracker.antiTheftTrackerApp.dtos.request.CommandRequest;
import com.antiTheftTracker.antiTheftTrackerApp.services.commandManagementServ.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommandController {
    private final CommandService commandService;

    @PostMapping("/issue")
    public ResponseEntity<Void> issueRemoteCommand(@RequestBody CommandRequest request) {
        commandService.issueRemoteCommand(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/lock/{deviceId}")
    public ResponseEntity<Void> lockDevice(@PathVariable String deviceId) {
        commandService.issueLockCommand(deviceId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/unlock/{deviceId}")
    public ResponseEntity<Void> unlockDevice(@PathVariable String deviceId) {
        commandService.issueUnlockCommand(deviceId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/wipe/{deviceId}")
    public ResponseEntity<Void> wipeDevice(@PathVariable String deviceId) {
        commandService.issueWipeCommand(deviceId);
        return ResponseEntity.accepted().build();
    }
}
