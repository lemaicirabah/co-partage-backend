//package com.userservice.mapper;
//
//import com.userservice.dto.ProjectDto;
//import com.userservice.entity.Project;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface ProjectMapper {
//    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
//
//    @Mapping(source = "creator", target = "creator")
//    @Mapping(source = "members", target = "members")
//    @Mapping(source = "tasks", target = "tasks")
//    ProjectDto projectToProjectDto(Project project);
//
//    @Mapping(source = "creator", target = "creator")
//    @Mapping(source = "members", target = "members")
//    @Mapping(source = "tasks", target = "tasks")
//    Project projectDtoToProject(ProjectDto projectDto);
//}
