package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EvaluationDto {
    private Long id;
    private String comments;
    private int rating;
    private UserDto evaluator;
    private UserDto evaluatee;
    private ProjectDto project;
}
