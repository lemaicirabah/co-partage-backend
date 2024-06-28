package com.userservice.mapper;

import com.userservice.dto.SkillDto;
import com.userservice.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDto skillToSkillDto(Skill skill);

    Skill skillDtoToSkill(SkillDto skillDto);
}
