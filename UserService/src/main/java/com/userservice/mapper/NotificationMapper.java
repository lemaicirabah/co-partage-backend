//package com.userservice.mapper;
//
//import com.userservice.dto.NotificationDto;
//import com.userservice.entity.Notification;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface NotificationMapper {
//    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
//
//    @Mapping(source = "recipient", target = "recipient")
//    NotificationDto notificationToNotificationDto(Notification notification);
//
//    @Mapping(source = "recipient", target = "recipient")
//    Notification notificationDtoToNotification(NotificationDto notificationDto);
//}
