package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.exception.ProjectNotFoundException;
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

        TaskEntity taskEntity = TaskMapper.toEntity(taskDto, project.get());
        return this.taskRepository.save(taskEntity);
    }

}
