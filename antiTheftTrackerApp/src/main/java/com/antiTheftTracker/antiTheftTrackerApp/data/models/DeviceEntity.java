package com.antiTheftTracker.antiTheftTrackerApp.data.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="devices")
public class DeviceEntity {
    @Id
    private String id;
    private String policyName;
    private String enrollmentTime;
    private double longitude;
    private double latitude;
    private boolean isStolen = false;
    private String lastSeen;
    private String lastKnownSim;
    private LocalDateTime theftReportTime = LocalDateTime.now();

    private String fcmToken;

    @OneToMany(mappedBy="device", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<DeviceDetails> deviceDetails;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TrustedContact> trustedContacts = new ArrayList<>();

    @ManyToOne
    private User user;
    @OneToMany(mappedBy="device", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Command> pendingCommands = new ArrayList<>();


}