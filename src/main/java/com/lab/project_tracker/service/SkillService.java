package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.skill.SkillDto;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.SkillEntity;

import java.util.Optional;

public interface SkillService {
    SkillEntity create(SkillDto skillDto);
    Optional<SkillEntity> findSkillByName(String name);
}
