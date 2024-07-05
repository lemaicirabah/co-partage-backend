package com.evaluationservice.service;

import com.evaluationservice.dto.EvaluationDto;
import com.evaluationservice.dto.ProjectDto;
import com.evaluationservice.dto.UserDto;
import com.evaluationservice.entity.Evaluation;
import com.evaluationservice.exception.ResourceNotFoundException;
import com.evaluationservice.mapper.EvaluationMapper;
import com.evaluationservice.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private RestTemplate restTemplate;

    public EvaluationDto createEvaluation(Long evaluatorId, Long evaluateeId, Long projectId, String comments, int rating) {
        // Récupérer les détails de l'évaluateur depuis le User Service
        UserDto evaluatorDto = restTemplate.getForObject("http://co-partage/users/" + evaluatorId, UserDto.class);
        // Récupérer les détails de l'évalué depuis le User Service
        UserDto evaluateeDto = restTemplate.getForObject("http://co-partage/users/" + evaluateeId, UserDto.class);
        // Récupérer les détails du projet depuis le Project Service
        ProjectDto projectDto = restTemplate.getForObject("http://co-partage/projects/" + projectId, ProjectDto.class);

        // Créer une nouvelle évaluation
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluatorId(evaluatorId);
        evaluation.setEvaluateeId(evaluateeId);
        evaluation.setProjectId(projectId);
        evaluation.setComments(comments);
        evaluation.setRating(rating);
        evaluation.setEvaluator(evaluatorDto);
        evaluation.setEvaluatee(evaluateeDto);
        evaluation.setProject(projectDto);

        // Sauvegarder l'évaluation dans la base de données
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        // Retourner l'évaluation sous forme de DTO
        return evaluationMapper.toDTO(savedEvaluation);
    }

    public EvaluationDto getEvaluation(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
        return evaluationMapper.toDTO(evaluation);
    }

    public EvaluationDto updateEvaluation(Long evaluationId, EvaluationDto evaluationDTO) {
        Evaluation existingEvaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));

        // Mettre à jour les détails de l'évaluation
        existingEvaluation.setComments(evaluationDTO.getComments());
        existingEvaluation.setRating(evaluationDTO.getRating());

        // Si nécessaire, mettre à jour les références aux autres microservices
        if (!existingEvaluation.getEvaluatorId().equals(evaluationDTO.getEvaluatorId())) {
            UserDto evaluatorDTO = restTemplate.getForObject("http://USER-SERVICE/users/" + evaluationDTO.getEvaluatorId(), UserDto.class);
            existingEvaluation.setEvaluatorId(evaluationDTO.getEvaluatorId());
            existingEvaluation.setEvaluator(evaluatorDTO);
        }

        if (!existingEvaluation.getEvaluateeId().equals(evaluationDTO.getEvaluateeId())) {
            UserDto evaluateeDto = restTemplate.getForObject("http://USER-SERVICE/users/" + evaluationDTO.getEvaluateeId(), UserDto.class);
            existingEvaluation.setEvaluateeId(evaluationDTO.getEvaluateeId());
            existingEvaluation.setEvaluatee(evaluateeDto);
        }

        if (!existingEvaluation.getProjectId().equals(evaluationDTO.getProjectId())) {
            ProjectDto projectDto = restTemplate.getForObject("http://PROJECT-SERVICE/projects/" + evaluationDTO.getProjectId(), ProjectDto.class);
            existingEvaluation.setProjectId(evaluationDTO.getProjectId());
            existingEvaluation.setProject(projectDto);
        }

        // Sauvegarder les modifications
        Evaluation savedEvaluation = evaluationRepository.save(existingEvaluation);
        return evaluationMapper.toDTO(savedEvaluation);
    }

    public void deleteEvaluation(Long evaluationId) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
        evaluationRepository.delete(evaluation);
    }

    public List<EvaluationDto> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationRepository.findAll();
        return evaluations.stream()
                .map(evaluationMapper::toDTO)
                .collect(Collectors.toList());
    }
}

