package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.exception.ProjectExistsException;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.repository.ProjectRepository;
import com.lab.project_tracker.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        if(findProjectByName(project.getName()).isPresent()){
            throw new ProjectExistsException(
                    String.format("A project with the name '%s' already exist",
                            project.getName()));
        }
        return this.projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectByName(String name) {
        return this.projectRepository.findProjectByName(name);
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return this.projectRepository.findById(id);
    }

}
