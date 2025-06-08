package com.lab.project_tracker.controller;


import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.dto.developer.DeveloperResponseDto;
import com.lab.project_tracker.mapper.DeveloperMapper;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.service.DeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("developers")
@Tag(name = "Skill Controller", description = "Manage all the Developer's urls")
public class DeveloperController {
    DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping(name = "add_developer", path = "/add")
    @Operation(summary = "Create developer",
            description = "This request inserts a developer to the database and returns " +
                          "the inserted developer ")
    public ResponseEntity<DeveloperResponseDto> addDeveloper(@RequestBody DeveloperDto developerDto){
        DeveloperEntity savedDeveloper = this.developerService.create(developerDto);
        DeveloperResponseDto savedDeveloperDto = DeveloperMapper.toResponseDto(savedDeveloper);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeveloperDto);
    }
}
