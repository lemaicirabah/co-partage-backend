package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private ProfileDto profile;
    private Set<ProjectDto> projects;
    private Set<UserGroupDto> groups;
    private Set<NotificationDto> notifications;
    private Set<EvaluationDto> evaluationsGiven;
    private Set<EvaluationDto> evaluationsReceived;
    private Set<TagDto> tags;
}
