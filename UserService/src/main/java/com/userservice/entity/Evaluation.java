package com.userservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;
    private int rating;

    @ManyToOne
    private User evaluator;

    @ManyToOne
    private User evaluatee;

    @ManyToOne
    private Project project;
}
