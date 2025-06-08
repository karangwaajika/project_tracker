package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.exception.ProjectNotFoundException;
import com.lab.project_tracker.exception.TaskExistsException;
import com.lab.project_tracker.mapper.TaskMapper;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.TaskEntity;
import com.lab.project_tracker.repository.TaskRepository;
import com.lab.project_tracker.service.ProjectService;
import com.lab.project_tracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;
    ProjectService projectService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectService projectService) {

        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }

    @Override
    public TaskEntity create(TaskDto taskDto) {
        Optional<Project> project = this.projectService.findProjectById(taskDto.getProjectId());
        if(project.isEmpty()){
            throw new ProjectNotFoundException(
                    String.format("A project with the Id '%d' doesn't exist",
                            taskDto.getProjectId()));
        }

        if(findTaskByTitle(taskDto.getTitle()).isPresent()){
            throw new TaskExistsException(
                    String.format("A task with the title '%s' already exist",
                            taskDto.getTitle()));
        }

        TaskEntity taskEntity = TaskMapper.toEntity(taskDto, project.get());
        return this.taskRepository.save(taskEntity);
    }

    @Override
    public Optional<TaskEntity> findTaskById(Long id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public Optional<TaskEntity> findTaskByTitle(String title) {
        return this.taskRepository.findTaskEntitiesByTitle(title);
    }

}
