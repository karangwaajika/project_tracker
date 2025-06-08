package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.model.DeveloperEntity;

public interface DeveloperService {
    DeveloperEntity create(DeveloperDto developerDto);
}
