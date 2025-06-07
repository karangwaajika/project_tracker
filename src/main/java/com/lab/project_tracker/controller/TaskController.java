package com.lab.project_tracker.controller;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.mapper.TaskMapper;
import com.lab.project_tracker.model.Task;
import com.lab.project_tracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
@Tag(name = "Task Controller", description = "Manage all the Task's urls")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(name = "add_task", path = "/add")
    @Operation(summary = "Create task",
            description = "This request inserts a task to the database and returns " +
                          "the inserted task ")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskDto taskDto){
        Task savedTask = this.taskService.create(taskDto);
        TaskResponseDto taskResponseDto= TaskMapper.toResponseDto(savedTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDto);
    }
}
