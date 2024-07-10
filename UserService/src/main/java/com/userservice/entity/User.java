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

    @ElementCollection
    private Set<Long> groups;

    @ElementCollection
    private Set<Long> notifications;

    @ElementCollection
    private Set<Long> evaluationsGiven;

    @ElementCollection
    private Set<Long> evaluationsReceived;

    @ManyToMany
    private Set<Tag> tags;
}
