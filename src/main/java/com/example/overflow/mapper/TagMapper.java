package com.example.overflow.mapper;

import com.example.overflow.dto.request.TagPostDto;
import com.example.overflow.dto.response.TagResponseDto;
import com.example.overflow.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagPostDtoToTag(TagPostDto requestBody);
//    Tag tagPatchDtoToTag(TagPatchDto requestBody);
    TagResponseDto tagToResponseDto(Tag tag);
//    List<TagResponseDto> tagsToResponseDtos(List<Tag> tagList);
}
