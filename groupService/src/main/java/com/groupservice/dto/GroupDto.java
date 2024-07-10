package com.groupservice.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDto {
    private Long id;
    private String name;
    private String description;
    private Long ownerId; // ID de l'owner
    private Set<Long> memberIds; // IDs des membres
    private Set<Long> projectIds; // IDs des projects
}
