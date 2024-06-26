package com.projectservice.mapper;

import com.projectservice.entity.Task;
import dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "assignee", target = "assignee")
    TaskDto taskToTaskDto(Task task);

    @Mapping(source = "assignee", target = "assignee")
    Task taskDtoToTask(TaskDto taskDto);
}

