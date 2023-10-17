package com.example.overflow.mapper;

import com.example.overflow.dto.request.TagPatchDto;
import com.example.overflow.dto.request.TagPostDto;
import com.example.overflow.dto.response.TagResponseDto;
import com.example.overflow.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-17T11:10:50+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag tagPostDtoToTag(TagPostDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setTagName( requestBody.getTagName() );
        tag.setContent( requestBody.getContent() );
        tag.setTagMentionCount( requestBody.getTagMentionCount() );

        return tag;
    }

    @Override
    public Tag tagPatchDtoToTag(TagPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setTagName( requestBody.getTagName() );
        tag.setContent( requestBody.getContent() );
        tag.setTagMentionCount( requestBody.getTagMentionCount() );

        return tag;
    }

    @Override
    public TagResponseDto tagToResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagResponseDto tagResponseDto = new TagResponseDto();

        tagResponseDto.setId( tag.getId() );
        tagResponseDto.setTagName( tag.getTagName() );
        tagResponseDto.setContent( tag.getContent() );
        tagResponseDto.setTagMentionCount( tag.getTagMentionCount() );

        return tagResponseDto;
    }

    @Override
    public List<TagResponseDto> tagsToResponseDtos(List<Tag> tagList) {
        if ( tagList == null ) {
            return null;
        }

        List<TagResponseDto> list = new ArrayList<TagResponseDto>( tagList.size() );
        for ( Tag tag : tagList ) {
            list.add( tagToResponseDto( tag ) );
        }

        return list;
    }
}
