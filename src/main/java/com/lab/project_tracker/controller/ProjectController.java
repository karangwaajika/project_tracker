package com.lab.project_tracker.controller;

import com.lab.project_tracker.exception.ProjectNotFoundException;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping(name = "view_project_by_id", path = "/view/{id}")
    @Operation(summary = "View Project",
            description = "Search and view only one project using project ID")
    public ResponseEntity<Optional<Project>> viewProject(@PathVariable Long id){
        Optional<Project> project = this.projectService.findProjectById(id);

        if(project.isEmpty()){
            throw new ProjectNotFoundException(
                    String.format("A project with the Id '%d' doesn't exist", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

}
