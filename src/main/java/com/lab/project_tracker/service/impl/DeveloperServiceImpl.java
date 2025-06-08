package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.mapper.DeveloperMapper;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.model.SkillEntity;
import com.lab.project_tracker.repository.DeveloperRepository;
import com.lab.project_tracker.service.DeveloperService;
import com.lab.project_tracker.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeveloperServiceImpl implements DeveloperService {
    SkillService skillService;
    DeveloperRepository developerRepository;

    public DeveloperServiceImpl(SkillService skillService,
                                DeveloperRepository developerRepository) {
        this.skillService = skillService;
        this.developerRepository = developerRepository;
    }

    @Override
    public DeveloperEntity create(DeveloperDto developerDto) {
        Set<SkillEntity> skills = new HashSet<>(skillService.findAllById(developerDto.getSkillIds()));
        DeveloperEntity developerEntity = DeveloperMapper.toEntity(developerDto, skills);
        return this.developerRepository.save(developerEntity);
    }
}
