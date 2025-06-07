package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.model.Task;

public interface TaskService {
    Task create(TaskDto taskDto);
}
