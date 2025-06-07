package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.ProjectDto;
import com.lab.project_tracker.dto.ProjectResponseDto;
import com.lab.project_tracker.exception.ProjectExistsException;
import com.lab.project_tracker.exception.ProjectNotFoundException;
import com.lab.project_tracker.mapper.ProjectMapper;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.repository.ProjectRepository;
import com.lab.project_tracker.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(ProjectDto projectDto) {
        if(findProjectByName(projectDto.getName()).isPresent()){
            throw new ProjectExistsException(
                    String.format("A project with the name '%s' already exist",
                            projectDto.getName()));
        }

        Project project = ProjectMapper.toEntity(projectDto);
        return this.projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectByName(String name) {
        return this.projectRepository.findProjectByName(name);
    }

    @Override
    public Page<ProjectResponseDto> findAll(Pageable pageable) {
        Page<Project> projectPage = this.projectRepository.findAll(pageable);
        //project -> ProjectMapper.toResponseDto(project)
        return projectPage.map(ProjectMapper::toResponseDto);
    }

    @Override
    public Project partialUpdate(ProjectDto projectDto, Long id) {
        Project project = findProjectById(id)
                .orElseThrow( () -> new ProjectNotFoundException(
                        String.format("A project with the Id '%d' doesn't exist", id))
                );
        // update only fields that are provided
        if(projectDto.getName() != null){
            project.setName(projectDto.getName());
        }
        if(projectDto.getDeadline() != null){
            project.setDeadline(projectDto.getDeadline());
        }
        if(projectDto.getDescription() != null){
            project.setDescription(projectDto.getDescription());
        }
        if(projectDto.getStatus() != null){
            project.setStatus(projectDto.getStatus());
        }

        return this.projectRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        if(findProjectById(id).isEmpty()){
            throw new ProjectExistsException(
                    String.format("A project with the name '%d' already exist",
                            id));
        }
        this.projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return this.projectRepository.findById(id);
    }

}
