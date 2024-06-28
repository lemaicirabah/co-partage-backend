package com.userservice.mapper;

import com.userservice.dto.TagDto;
import com.userservice.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto tagToTagDto(Tag tag);

    Tag tagDtoToTag(TagDto tagDto);
}
