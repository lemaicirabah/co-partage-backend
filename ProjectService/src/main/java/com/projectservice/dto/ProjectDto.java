package com.projectservice.dto;

import java.util.Set;

public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private Long creator;
    private Set<Long> members;
    private Set<TaskDto> tasks;
    private Set<Long> evaluations;

    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter et Setter pour description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et Setter pour creator
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    // Getter et Setter pour members
    public Set<Long> getMembers() {
        return members;
    }

    public void setMembers(Set<Long> members) {
        this.members = members;
    }

    // Getter et Setter pour tasks
    public Set<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskDto> tasks) {
        this.tasks = tasks;
    }

    // Getter et Setter pour evaluations
    public Set<Long> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Long> evaluations) {
        this.evaluations = evaluations;
    }
}
