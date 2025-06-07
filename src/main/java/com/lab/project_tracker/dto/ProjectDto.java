package com.lab.project_tracker.dto;

import com.lab.project_tracker.util.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate deadline;
    private ProjectStatus status;
    private LocalDateTime createdAt = LocalDateTime.now();
}
