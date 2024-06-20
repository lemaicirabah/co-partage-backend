package com.projectservice.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Creator is mandatory")
    private Long creator;

    @ElementCollection
    private Set<Long> members;

    @ElementCollection
    private Set<Long> evaluations;

    @OneToMany(cascade = CascadeType.ALL) // Cascade save operations
    @Valid
    private Set<Task> tasks;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Set<Long> getMembers() {
        return members;
    }

    public void setMembers(Set<Long> members) {
        this.members = members;
    }

    public Set<Long> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Long> evaluations) {
        this.evaluations = evaluations;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
