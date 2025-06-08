package com.lab.project_tracker.service.impl;

import com.lab.project_tracker.dto.skill.SkillDto;
import com.lab.project_tracker.exception.SkillExistsException;
import com.lab.project_tracker.mapper.SkillMapper;
import com.lab.project_tracker.model.SkillEntity;
import com.lab.project_tracker.repository.SkillRepository;
import com.lab.project_tracker.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    SkillRepository skillRepository;
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public SkillEntity create(SkillDto skillDto) {
        if(findSkillByName(skillDto.getName()).isPresent()){
            throw new SkillExistsException(
                    String.format("A project with the name '%s' already exist",
                            skillDto.getName()));
        }
        SkillEntity skillEntity = SkillMapper.toEntity(skillDto);
        return this.skillRepository.save(skillEntity);
    }

    @Override
    public Optional<SkillEntity> findSkillByName(String name) {
        return this.skillRepository.findSkillByName(name);
    }
}
