package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.model.TaskEntity;

import java.util.Optional;

public interface DeveloperService {
    DeveloperEntity create(DeveloperDto developerDto);
    Optional<DeveloperEntity> findDeveloperById(Long id);
    Optional<DeveloperEntity> findDeveloperByEmail(String email);
}
