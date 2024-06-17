package com.projectservice.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long assignee;

    private TaskStatus status;

    private Date deadline;
}
