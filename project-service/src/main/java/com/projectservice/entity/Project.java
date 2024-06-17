package com.projectservice.entity;

import jakarta.persistence.*;
import java.util.Set;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long creator;

    @ElementCollection
    private Set<Long> members;

    @ElementCollection
    private Set<Long> evaluations;

    @OneToMany(cascade = CascadeType.ALL) // Cascade save operations
    private Set<Task> skills;



}
