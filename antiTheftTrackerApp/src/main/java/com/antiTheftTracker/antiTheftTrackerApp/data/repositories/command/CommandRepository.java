package com.antiTheftTracker.antiTheftTrackerApp.data.repositories.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, String> {
}
