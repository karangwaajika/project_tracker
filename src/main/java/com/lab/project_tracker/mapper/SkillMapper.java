package com.lab.project_tracker.mapper;

import com.lab.project_tracker.dto.skill.SkillDto;
import com.lab.project_tracker.dto.skill.SkillResponseDto;
import com.lab.project_tracker.model.SkillEntity;

public class SkillMapper {
    public static SkillResponseDto toResponseDto(SkillEntity skillEntity) {
        SkillResponseDto dto = new SkillResponseDto();
        dto.setId(skillEntity.getId());
        dto.setName(skillEntity.getName());

        return dto;
    }

    public static SkillEntity toEntity(SkillDto dto) {
        SkillEntity skillEntity = new SkillEntity();
        skillEntity.setName(dto.getName());

        return skillEntity;
    }
}
