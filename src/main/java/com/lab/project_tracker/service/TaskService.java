package com.lab.project_tracker.service;


import com.lab.project_tracker.dto.ProjectDto;
import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {
    TaskEntity create(TaskDto taskDto);
    Optional<TaskEntity> findTaskById(Long id);
    Optional<TaskEntity> findTaskByTitle(String title);
    Page<TaskResponseDto> findAll(Pageable pageable);
    TaskEntity partialUpdate(TaskDto taskDto, Long id);
}
