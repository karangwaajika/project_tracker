package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByName(String name);
}
