package com.userservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ElementCollection
    private Set<Long> projects;

    @ManyToMany
    private Set<UserGroup> groups;

    @OneToMany(mappedBy = "recipient")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "evaluator")
    private Set<Evaluation> evaluationsGiven;

    @OneToMany(mappedBy = "evaluatee")
    private Set<Evaluation> evaluationsReceived;

    @ManyToMany
    private Set<Tag> tags;
}
