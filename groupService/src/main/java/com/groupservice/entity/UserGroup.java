package com.groupservice.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`groups`")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long ownerId;

    @ElementCollection
    private Set<Long> memberIds = new HashSet<>();

    @ElementCollection
    private Set<Long> projectIds = new HashSet<>();

}

