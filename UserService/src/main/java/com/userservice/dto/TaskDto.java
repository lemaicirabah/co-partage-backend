package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long assignee;
    private String status;
    private Date deadline;
}
