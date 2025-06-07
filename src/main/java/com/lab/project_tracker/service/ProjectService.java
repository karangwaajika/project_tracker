package com.lab.project_tracker.service;

import com.lab.project_tracker.model.Project;

import java.util.Optional;

public interface ProjectService {
    Project create(Project project);
    Optional<Project> findProjectById(Long id);
    Optional<Project> findProjectByName(String name);
}
