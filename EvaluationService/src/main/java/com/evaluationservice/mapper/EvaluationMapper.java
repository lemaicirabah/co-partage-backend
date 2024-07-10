package com.evaluationservice.mapper;

import com.evaluationservice.dto.EvaluationDto;
import com.evaluationservice.entity.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    @Mapping(target = "evaluatorId", source = "evaluatorId")
    EvaluationDto toDTO(Evaluation evaluation);

    @Mapping(target = "evaluatorId", source = "evaluatorId")
    Evaluation toEntity(EvaluationDto evaluationDTO);
}
