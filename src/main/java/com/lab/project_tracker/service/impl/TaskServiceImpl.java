package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.ProjectDto;
import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.exception.ProjectNotFoundException;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.Task;
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
    public TaskResponseDto create(TaskDto taskDto) {
        Optional<Project> project = this.projectService.findProjectById(taskDto.getProjectId());
        if(project.isEmpty()){
            throw new ProjectNotFoundException(
                    String.format("A project with the Id '%d' doesn't exist",
                            taskDto.getProjectId()));
        }
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .dueDate(taskDto.getDueDate())
                .project(project.get())
                .build();
        Task savedTask = this.taskRepository.save(task);
        return mapToResponseDto(savedTask);
    }

    private TaskResponseDto mapToResponseDto(Task task) {
        Project project = task.getProject();

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDeadline(project.getDeadline());
        projectDto.setStatus(project.getStatus());
        projectDto.setDescription(project.getDescription());
        projectDto.setCreatedAt(project.getCreatedAt());

        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setProject(projectDto);

        return dto;
    }
}
