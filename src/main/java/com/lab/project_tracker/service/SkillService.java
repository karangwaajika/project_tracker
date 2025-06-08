package com.lab.project_tracker.service;

import com.lab.project_tracker.dto.skill.SkillDto;
import com.lab.project_tracker.dto.skill.SkillResponseDto;
import com.lab.project_tracker.dto.task.TaskResponseDto;
import com.lab.project_tracker.model.Project;
import com.lab.project_tracker.model.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SkillService {
    SkillEntity create(SkillDto skillDto);
    Optional<SkillEntity> findSkillByName(String name);
    Optional<SkillEntity> findSkillById(Long id);
    Set<SkillEntity> findAllById(Set<Long> ids);
    Page<SkillResponseDto> findAll(Pageable pageable);


}
