package com.evaluationservice.service;

import com.evaluationservice.client.ProjectServiceClient;
import com.evaluationservice.client.UserServiceClient;
import com.evaluationservice.dto.EvaluationDto;
import com.evaluationservice.entity.Evaluation;
import com.evaluationservice.exception.ResourceNotFoundException;
import com.evaluationservice.mapper.EvaluationMapper;
import com.evaluationservice.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProjectServiceClient projectServiceClient;

    public EvaluationDto createEvaluation(EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationMapper.toEntity(evaluationDto);
        evaluation = evaluationRepository.save(evaluation);
        userServiceClient.addGivenEvaluationToUser(evaluation.getEvaluatorId(), evaluation.getId());
        userServiceClient.addReceiveEvaluationToUser(evaluation.getEvaluateeId(), evaluation.getId());
        projectServiceClient.addEvaluation(evaluation.getProjectId(), evaluation.getId());
        return evaluationMapper.toDTO(evaluation);
    }

    public EvaluationDto getEvaluation(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found with id: " + id));
        return evaluationMapper.toDTO(evaluation);
    }

    public EvaluationDto updateEvaluation(Long id, EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found with id: " + id));
        evaluation.setComments(evaluationDto.getComments());
        evaluation.setRating(evaluationDto.getRating());
        evaluation = evaluationRepository.save(evaluation);
        return evaluationMapper.toDTO(evaluation);
    }

    public void deleteEvaluation(Long id) {

        Evaluation evaluation = evaluationRepository.findById(id).orElse(null);

        if (evaluation == null) {
            return;
        }
        evaluationRepository.deleteById(id);
        projectServiceClient.removeEvaluation(evaluation.getProjectId(), evaluation.getId());
        userServiceClient.removeGivenEvaluationToUser(evaluation.getEvaluatorId(), evaluation.getId());
        userServiceClient.removeReceiveEvaluationToUser(evaluation.getEvaluateeId(), evaluation.getId());
    }

    public List<EvaluationDto> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        return evaluations.stream().map(evaluationMapper::toDTO).collect(Collectors.toList());
    }
}
