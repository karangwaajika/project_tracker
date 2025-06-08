package com.lab.project_tracker.mapper;

import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.dto.developer.DeveloperResponseDto;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.model.SkillEntity;
import com.lab.project_tracker.repository.SkillRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DeveloperMapper {
    private final SkillRepository skillRepository;

    public DeveloperMapper(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public DeveloperResponseDto toDeveloperResponse(DeveloperEntity developerEntity) {
        DeveloperResponseDto dto = DeveloperResponseDto.builder()
                .name(developerEntity.getName())
                .id(developerEntity.getId())
                .email(developerEntity.getEmail())
                .skills(developerEntity.getSkills().stream().map(SkillMapper::toResponseDto)
                        .collect(Collectors.toSet()))
                .build();

        return dto;
    }

    public DeveloperEntity toEntity(DeveloperDto developerDto) {
        Set<SkillEntity> skills = new HashSet<>(skillRepository.
                findAllById(developerDto.getSkillIds()));
        if (skills.size() != developerDto.getSkillIds().size()) {
            throw new IllegalArgumentException("Some skill IDs are invalid.");
        }

        return DeveloperEntity.builder()
                .name(developerDto.getName())
                .email(developerDto.getEmail())
                .skills(skills)
                .build();
    }
}
