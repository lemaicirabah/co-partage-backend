package com.projectservice.mapper;

import com.projectservice.entity.Project;
import dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "members", target = "members")
    @Mapping(source = "tasks", target = "tasks")
    @Mapping(source = "evaluations", target = "evaluations")
    ProjectDto projectToProjectDto(Project project);

    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "members", target = "members")
    @Mapping(source = "tasks", target = "tasks")
    @Mapping(source = "evaluations", target = "evaluations")
    Project projectDtoToProject(ProjectDto projectDto);
}


