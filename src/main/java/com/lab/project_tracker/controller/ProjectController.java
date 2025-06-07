package com.lab.project_tracker.controller;

import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
@Tag(name = "Project Controller", description = "Manage all the Project's urls")
public class ProjectController {
    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(name = "add_project", path = "/add")
    @Operation(summary = "Create project",
            description = "This request inserts a project to the database and returns the inserted project ")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        Project savedProject = this.projectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

}
