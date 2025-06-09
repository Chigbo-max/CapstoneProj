package com.antiTheftTracker.antiTheftTrackerApp.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TrustedContact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String phoneNumber;
    private String email;
    private boolean smsEnabled = true;
    private boolean emailEnabled = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference
    private DeviceEntity device;
}
