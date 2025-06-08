package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {
}
