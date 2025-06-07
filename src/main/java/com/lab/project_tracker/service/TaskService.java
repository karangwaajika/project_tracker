package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.model.TaskEntity;

public interface TaskService {
    TaskEntity create(TaskDto taskDto);
}
