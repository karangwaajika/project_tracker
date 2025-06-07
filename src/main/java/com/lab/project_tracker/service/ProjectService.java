package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.ProjectDto;
import com.lab.project_tracker.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjectService {
    Project create(Project project);
    Optional<Project> findProjectById(Long id);
    Optional<Project> findProjectByName(String name);
    Page<Project> findAll(Pageable pageable);
    Project partialUpdate(ProjectDto projectdto, Long id);
    void deleteById(Long id);


}
