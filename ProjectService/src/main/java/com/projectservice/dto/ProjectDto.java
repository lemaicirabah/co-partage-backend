package com.projectservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProjectDto {
    // Getter et Setter pour id
    private Long id;
    // Getter et Setter pour title
    private String title;
    // Getter et Setter pour description
    private String description;
    // Getter et Setter pour creator
    private Long creator;
    // Getter et Setter pour members
    private Set<Long> members;
    // Getter et Setter pour tasks
    private Set<TaskDto> tasks;
    // Getter et Setter pour evaluations
    private Set<Long> evaluations;

}
