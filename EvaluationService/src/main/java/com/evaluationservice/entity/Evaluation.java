package com.evaluationservice.entity;

import com.evaluationservice.dto.ProjectDto;
import com.evaluationservice.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;
    private int rating;

    private Long evaluatorId; // Utiliser l'ID de l'évaluateur
    private Long evaluateeId; // Utiliser l'ID de l'évalué
    private Long projectId;   // Utiliser l'ID du projet

    @Transient
    private UserDto evaluator;

    @Transient
    private UserDto evaluatee;

    @Transient
    private ProjectDto project;
}
