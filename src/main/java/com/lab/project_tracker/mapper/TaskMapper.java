package com.lab.project_tracker.mapper;

import com.lab.project_tracker.dto.ProjectDto;
import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.Task;

public class TaskMapper {
    public static Task toEntity(TaskDto taskDto, Project project) {
        return Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .dueDate(taskDto.getDueDate())
                .project(project)
                .build();
    }

    public static TaskResponseDto toResponseDto(Task task) {
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
