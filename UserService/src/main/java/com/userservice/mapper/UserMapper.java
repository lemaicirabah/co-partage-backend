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
    UserDto userToUserDto(User user);

    @Mapping(source = "profile", target = "profile")
    User userDtoToUser(UserDto userDto);
}
