package com.example.overflow.controller;

import com.example.overflow.dto.request.TagPatchDto;
import com.example.overflow.dto.request.TagPostDto;
import com.example.overflow.dto.response.MultiResponseDto;
import com.example.overflow.dto.response.TagResponseDto;
import com.example.overflow.entity.Tag;
import com.example.overflow.mapper.TagMapper;
import com.example.overflow.service.TagService;
import com.example.overflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final static String TAG_URL = "/tag";
    private final TagService tagService;
    private final TagMapper mapper;

    @PostMapping
    public ResponseEntity postTag(@Valid @RequestBody TagPostDto requestBody) {
        Tag tag = mapper.tagPostDtoToTag(requestBody);
        Tag createdTag = tagService.createTag(tag);
        TagResponseDto response = mapper.tagToResponseDto(createdTag);

        URI location = UriCreator.createUri(TAG_URL, createdTag.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping
    public ResponseEntity patchTag(@Valid @RequestBody TagPatchDto requestBody) {
        Tag tag = mapper.tagPatchDtoToTag(requestBody);
        Tag updateTag = tagService.updateTag(tag);

        return new ResponseEntity<>(mapper.tagToResponseDto(updateTag), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity getTag(@PathVariable("id") @Positive Integer id) {
        Tag tag = tagService.findTag(id);

        return new ResponseEntity<>(mapper.tagToResponseDto(tag), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page,
                                  @Positive @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "popular") String order) {
        Page<Tag> pageTags = tagService.findTags(page - 1, size, order);
        List<Tag> tags = pageTags.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.tagsToResponseDtos(tags), pageTags), HttpStatus.OK);
    }

}
