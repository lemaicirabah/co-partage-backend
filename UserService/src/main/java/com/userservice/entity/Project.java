package com.userservice.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "project")
    private Set<Evaluation> evaluations;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UserGroup group;
}
