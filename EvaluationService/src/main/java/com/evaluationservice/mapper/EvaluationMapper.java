package com.evaluationservice.mapper;

import com.evaluationservice.dto.EvaluationDto;
import com.evaluationservice.entity.Evaluation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluationMapper {
    EvaluationDto toDTO(Evaluation evaluation);
    Evaluation toEntity(EvaluationDto evaluationDTO);
}

