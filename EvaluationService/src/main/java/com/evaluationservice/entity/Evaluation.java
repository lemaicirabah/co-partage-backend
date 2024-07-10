package com.evaluationservice.entity;

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

}
