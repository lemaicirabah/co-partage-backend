package com.groupservice.mapper;

import com.groupservice.dto.GroupDto;
import com.groupservice.entity.UserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "ownerId", target = "ownerId")
    @Mapping(source = "memberIds", target = "memberIds")
    @Mapping(source = "projectIds", target = "projectIds")

    GroupDto groupToGroupDto(UserGroup userGroup);

    @Mapping(source = "ownerId", target = "ownerId")
    @Mapping(source = "memberIds", target = "memberIds")
    @Mapping(source = "projectIds", target = "projectIds")

    UserGroup groupDtoToGroup(GroupDto groupDto);
}
