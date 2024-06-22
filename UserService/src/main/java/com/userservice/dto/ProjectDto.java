package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private UserDto creator;
    private Set<UserDto> members;
    private Set<TaskDto> tasks;
    private Set<EvaluationDto> evaluations;
}
