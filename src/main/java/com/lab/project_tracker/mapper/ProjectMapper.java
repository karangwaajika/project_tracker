package com.lab.project_tracker.mapper;

import com.lab.project_tracker.dto.ProjectResponseDto;
import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskDtoSummary;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {
    public static ProjectResponseDto toResponseDto(Project project) {
        ProjectResponseDto dto = new ProjectResponseDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setDeadline(project.getDeadline());
        dto.setStatus(project.getStatus());
        dto.setCreatedAt(project.getCreatedAt());


        List<TaskDtoSummary> taskDtoList = new ArrayList<>();

        for (TaskEntity task : project.getTaskEntities()) {
            TaskDtoSummary taskDto = toTaskDto(task);
            taskDtoList.add(taskDto);
        }

        dto.setTasks(taskDtoList);
        return dto;
    }

    /* this prevents the invite loop because there is no project again
    * as a task is expected to have project inside it.*/

    private static TaskDtoSummary toTaskDto(TaskEntity task) {
        // without including project
        TaskDtoSummary dto = new TaskDtoSummary();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        return dto;
    }
}
