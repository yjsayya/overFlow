package com.example.overflow.controller;

import com.example.overflow.dto.request.TagPostDto;
import com.example.overflow.dto.response.TagResponseDto;
import com.example.overflow.entity.Tag;
import com.example.overflow.mapper.TagMapper;
import com.example.overflow.service.TagService;
import com.example.overflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final static String TAG_DEFAULT_URL = "/tag";
    private final TagService tagService;
    private final TagMapper mapper;

    @PostMapping
    public ResponseEntity postTag(@Valid @RequestBody TagPostDto requestBody) {
        Tag tag = mapper.tagPostDtoToTag(requestBody);
        Tag createdTag = tagService.createTag(tag);
        TagResponseDto response = mapper.tagToResponseDto(createdTag);

        URI location = UriCreator.createUri(TAG_DEFAULT_URL, createdTag.getId());
        return ResponseEntity.created(location).body(response);
    }

}
