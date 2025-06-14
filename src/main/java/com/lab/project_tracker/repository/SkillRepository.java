package com.lab.project_tracker.repository;

import com.lab.project_tracker.model.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    Optional<SkillEntity> findSkillByName(String name);
}
