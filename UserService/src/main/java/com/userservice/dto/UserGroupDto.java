package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserGroupDto {
    private Long id;
    private String name;
    private String description;
    private UserDto owner;
    private Set<UserDto> members;
    private Set<ProjectDto> projects;
}
