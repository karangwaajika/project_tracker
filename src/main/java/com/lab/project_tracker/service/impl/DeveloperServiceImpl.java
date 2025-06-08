package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.developer.DeveloperDto;
import com.lab.project_tracker.exception.DeveloperExistsException;
import com.lab.project_tracker.mapper.DeveloperMapper;
import com.lab.project_tracker.model.DeveloperEntity;
import com.lab.project_tracker.model.SkillEntity;
import com.lab.project_tracker.repository.DeveloperRepository;
import com.lab.project_tracker.service.DeveloperService;
import com.lab.project_tracker.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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
        if(findDeveloperByEmail(developerDto.getEmail()).isPresent()){
            throw new DeveloperExistsException(
                    String.format("A developer with the name '%s' already exist",
                    developerDto.getEmail()));
        }
        Set<SkillEntity> skills = new HashSet<>(skillService.findAllById(developerDto.getSkillIds()));
        DeveloperEntity developerEntity = DeveloperMapper.toEntity(developerDto, skills);
        return this.developerRepository.save(developerEntity);
    }

    @Override
    public Optional<DeveloperEntity> findDeveloperById(Long id) {
        return this.developerRepository.findById(id);
    }

    @Override
    public Optional<DeveloperEntity> findDeveloperByEmail(String email) {
        return this.developerRepository.findDeveloperByEmail(email);
    }
}
