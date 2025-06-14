package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.task.TaskDto;
import com.lab.project_tracker.dto.task.TaskResponseDto;
import com.lab.project_tracker.exception.DeveloperNotFoundException;
import com.lab.project_tracker.exception.ProjectNotFoundException;
import com.lab.project_tracker.exception.TaskExistsException;
import com.lab.project_tracker.exception.TaskNotFoundException;
import com.lab.project_tracker.mapper.TaskMapper;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.TaskEntity;
import com.lab.project_tracker.repository.TaskRepository;
import com.lab.project_tracker.service.DeveloperService;
import com.lab.project_tracker.service.ProjectService;
import com.lab.project_tracker.service.TaskService;
import com.lab.project_tracker.util.DeveloperTaskCount;
import com.lab.project_tracker.util.TaskStatusCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;
    ProjectService projectService;
    DeveloperService developerService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectService projectService,
                           DeveloperService developerService) {

        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.developerService = developerService;
    }

    @Override
    public TaskEntity create(TaskDto taskDto) {
        Optional<Project> project = this.projectService.findProjectById(taskDto.getProjectId());
        if(project.isEmpty()){
            throw new ProjectNotFoundException(
                    String.format("A project with the Id '%d' doesn't exist",
                            taskDto.getProjectId()));
        }

        if(findTaskByTitle(taskDto.getTitle()).isPresent()){
            throw new TaskExistsException(
                    String.format("A task with the title '%s' already exist",
                            taskDto.getTitle()));
        }

        TaskEntity taskEntity = TaskMapper.toEntity(taskDto, project.get());
        return this.taskRepository.save(taskEntity);
    }

    @Override
    public Optional<TaskEntity> findTaskById(Long id) {
        return this.taskRepository.findById(id);
    }

    @Override
    public Optional<TaskEntity> findTaskByTitle(String title) {
        return this.taskRepository.findTaskEntitiesByTitle(title);
    }

    @Override
    public Page<TaskResponseDto> findAll(Pageable pageable) {
        Page<TaskEntity> taskEntityPage = this.taskRepository.findAll(pageable);

        return taskEntityPage.map(TaskMapper::toResponseDto);
    }

    @Override
    public TaskEntity partialUpdate(TaskDto taskDto, Long id) {
        TaskEntity taskEntity = findTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("A task with the Id '%d' doesn't exist", id)));
        if(taskDto.getTitle() != null){
            taskEntity.setTitle(taskDto.getTitle());
        }
        if(taskDto.getDescription() != null){
            taskEntity.setDescription(taskDto.getDescription());
        }
        if(taskDto.getDueDate() != null){
            taskEntity.setDueDate(taskDto.getDueDate());
        }
        if(taskDto.getStatus() != null){
            taskEntity.setStatus(taskDto.getStatus());
        }
        if(taskDto.getProjectId() != null){
            Optional<Project> project = this.projectService.findProjectById(taskDto.getProjectId());
            if(project.isEmpty()){
                throw new ProjectNotFoundException(
                        String.format("A project with the Id '%d' doesn't exist",
                                taskDto.getProjectId()));
            }
            taskEntity.setProject(project.get());
        }
        return this.taskRepository.save(taskEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(findTaskById(id).isEmpty()){
            throw new TaskNotFoundException(
                    String.format("A task with the Id '%d' doesn't exist", id));
        }
        this.taskRepository.deleteById(id);
    }

    @Override
    public void assignTaskToDeveloper(Long taskId, Long developerId) {
        TaskEntity task = this.taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(
                        String.format("A task with the Id '%d' doesn't exist", taskId)
                ));
        DeveloperEntity developer = this.developerService.findDeveloperById(developerId)
                .orElseThrow(() -> new DeveloperNotFoundException(
                        String.format("A developer with the Id '%d' doesn't exist", developerId)
                ));

        task.setDeveloper(developer);
        this.taskRepository.save(task);
    }

    @Override
    public List<TaskResponseDto> findAllByOrderByDueDateAsc() {
        List<TaskEntity> tasks = taskRepository.findAllByOrderByDueDateAsc();
        return tasks.stream().map(TaskMapper::toResponseDto).toList();
    }

    @Override
    public List<TaskResponseDto> findOverdueTasks() {
        List<TaskEntity> tasks = this.taskRepository.findOverdueTasks();
        return tasks.stream().map(TaskMapper::toResponseDto).toList();
    }

    @Override
    public List<DeveloperTaskCount> findTopDevelopers(Pageable pageable) {
        return this.taskRepository.findTopDevelopers(pageable);
    }

    @Override
    public List<TaskStatusCount> countTasksByStatus() {
        return this.taskRepository.countTasksByStatus();
    }


}
