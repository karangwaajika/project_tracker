package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
    Optional<DeveloperEntity> findDeveloperByEmail(String email);
}
