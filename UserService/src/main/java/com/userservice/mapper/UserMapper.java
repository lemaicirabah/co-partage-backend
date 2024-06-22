package com.userservice.mapper;

import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "projects", target = "projects")
    @Mapping(source = "groups", target = "groups")
    @Mapping(source = "notifications", target = "notifications")
    @Mapping(source = "evaluationsGiven", target = "evaluationsGiven")
    @Mapping(source = "evaluationsReceived", target = "evaluationsReceived")
    @Mapping(source = "tags", target = "tags")
    UserDto userToUserDto(User user);

    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "projects", target = "projects")
    @Mapping(source = "groups", target = "groups")
    @Mapping(source = "notifications", target = "notifications")
    @Mapping(source = "evaluationsGiven", target = "evaluationsGiven")
    @Mapping(source = "evaluationsReceived", target = "evaluationsReceived")
    @Mapping(source = "tags", target = "tags")
    User userDtoToUser(UserDto userDto);
}
