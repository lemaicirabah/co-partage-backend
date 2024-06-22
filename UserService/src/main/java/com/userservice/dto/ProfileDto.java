package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String bio;
    private Set<SkillDto> skills;
}
