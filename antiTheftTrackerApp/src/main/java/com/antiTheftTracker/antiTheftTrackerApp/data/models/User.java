package com.antiTheftTracker.antiTheftTrackerApp.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name="Users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<DeviceEntity> devices;
}