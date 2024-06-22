package com.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NotificationDto {
    private Long id;
    private String message;
    private Date timestamp;
    private UserDto recipient;
}
