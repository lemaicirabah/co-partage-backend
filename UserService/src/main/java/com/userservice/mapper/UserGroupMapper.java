//package com.userservice.mapper;
//
//import com.userservice.dto.UserGroupDto;
//import com.userservice.entity.UserGroup;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface UserGroupMapper {
//    UserGroupMapper INSTANCE = Mappers.getMapper(UserGroupMapper.class);
//
//    @Mapping(source = "owner", target = "owner")
//    @Mapping(source = "members", target = "members")
//    @Mapping(source = "projects", target = "projects")
//    UserGroupDto groupToGroupDto(UserGroup group);
//
//    @Mapping(source = "owner", target = "owner")
//    @Mapping(source = "members", target = "members")
//    @Mapping(source = "projects", target = "projects")
//    UserGroup groupDtoToGroup(UserGroupDto groupDto);
//}
