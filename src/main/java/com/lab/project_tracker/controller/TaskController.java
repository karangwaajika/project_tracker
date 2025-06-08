package com.lab.project_tracker.controller;

import com.lab.project_tracker.dto.TaskDto;
import com.lab.project_tracker.dto.TaskResponseDto;
import com.lab.project_tracker.exception.TaskNotFoundException;
import com.lab.project_tracker.mapper.TaskMapper;
import com.lab.project_tracker.model.TaskEntity;
import com.lab.project_tracker.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        TaskEntity savedTaskEntity = this.taskService.create(taskDto);
        TaskResponseDto taskResponseDto= TaskMapper.toResponseDto(savedTaskEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponseDto);
    }

    @GetMapping(name = "view_task_by_id", path = "/view/{id}")
    @Operation(summary = "View Project",
            description = "Search and view only one task using task ID")
    public ResponseEntity<TaskResponseDto> viewTask(@PathVariable Long id){
        Optional<TaskEntity> task = this.taskService.findTaskById(id);

        if(task.isEmpty()){
            throw new TaskNotFoundException(
                    String.format("A task with the Id '%d' doesn't exist", id));
        }
        TaskResponseDto taskResponseDto = TaskMapper.toResponseDto(task.get());
        return ResponseEntity.status(HttpStatus.OK).body(taskResponseDto);
    }

    @GetMapping(name = "view_tasks", path = "view")
    @Operation(summary = "View Tasks",
            description = "This method applies pagination for efficient retrieval " +
                          "of tasks list")
    public Page<TaskResponseDto> viewProjects(Pageable pageable){
        return this.taskService.findAll(pageable);
    }

}
