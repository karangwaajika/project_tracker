package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.model.TaskEntity;

import java.util.Optional;

public interface TaskService {
    TaskEntity create(TaskDto taskDto);
    Optional<TaskEntity> findTaskById(Long id);
    Optional<TaskEntity> findTaskByTitle(String title);
}
