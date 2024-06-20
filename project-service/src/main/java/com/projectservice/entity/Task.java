package com.projectservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Task title is mandatory")
    private String title;

    private String description;

    private Long assignee;

    @NotNull(message = "Task status is mandatory")
    private TaskStatus status;

    private Date deadline;


    // Getters and Setters

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

    public Long getAssignee() {
        return assignee;
    }

    public void setAssignee(Long assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
