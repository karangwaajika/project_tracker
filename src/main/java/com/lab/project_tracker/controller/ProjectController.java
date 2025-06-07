package com.lab.project_tracker.controller;

import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
public class ProjectController {
    ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(name = "add_project", path = "/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        Project savedProject = this.projectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

}
