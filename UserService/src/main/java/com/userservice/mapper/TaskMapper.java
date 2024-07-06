//package com.userservice.mapper;
//
//import com.userservice.dto.TaskDto;
//import com.userservice.entity.Task;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface TaskMapper {
//    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
//
//    @Mapping(source = "assignee", target = "assignee")
//    TaskDto taskToTaskDto(Task task);
//
//    @Mapping(source = "assignee", target = "assignee")
//    Task taskDtoToTask(TaskDto taskDto);
//}
