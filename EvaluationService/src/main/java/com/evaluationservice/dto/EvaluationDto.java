package com.evaluationservice.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EvaluationDto {
    private Long id;
    private String comments;
    private int rating;
    private Long evaluatorId;
    private Long evaluateeId;
    private Long projectId;

    // Utilisation des DTO pour les détails si nécessaire
    private UserDto evaluator;
    private UserDto evaluatee;
    private ProjectDto project;

    // Getters et setters
}
