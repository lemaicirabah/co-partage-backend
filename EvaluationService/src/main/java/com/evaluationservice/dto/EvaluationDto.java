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
    // Getters et setters
}
