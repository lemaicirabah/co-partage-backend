package com.userservice.mapper;

import com.userservice.dto.EvaluationDto;
import com.userservice.entity.Evaluation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EvaluationMapper {
    EvaluationMapper INSTANCE = Mappers.getMapper(EvaluationMapper.class);

    @Mapping(source = "evaluator", target = "evaluator")
    @Mapping(source = "evaluatee", target = "evaluatee")
    @Mapping(source = "project", target = "project")
    EvaluationDto evaluationToEvaluationDto(Evaluation evaluation);

    @Mapping(source = "evaluator", target = "evaluator")
    @Mapping(source = "evaluatee", target = "evaluatee")
    @Mapping(source = "project", target = "project")
    Evaluation evaluationDtoToEvaluation(EvaluationDto evaluationDto);
}
