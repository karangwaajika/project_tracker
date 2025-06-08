package com.lab.project_tracker.service;


import com.lab.project_tracker.dto.task.TaskDto;
import com.lab.project_tracker.dto.task.TaskResponseDto;
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
    void deleteById(Long id);
}
