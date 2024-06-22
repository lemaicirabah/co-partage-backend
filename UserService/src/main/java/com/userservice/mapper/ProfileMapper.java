package com.userservice.mapper;

import com.userservice.dto.ProfileDto;
import com.userservice.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDto profileToProfileDto(Profile profile);

    Profile profileDtoToProfile(ProfileDto profileDto);
}
