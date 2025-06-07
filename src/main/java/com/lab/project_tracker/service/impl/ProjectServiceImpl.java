package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.repository.ProjectRepository;
import com.lab.project_tracker.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    @Override
    public Project create(Project project) {
        return this.projectRepository.save(project);
    }
}
