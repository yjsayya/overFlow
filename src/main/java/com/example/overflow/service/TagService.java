package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.entity.Tag;
import com.example.overflow.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    public Tag createTag(Tag tag) {
        Optional<Tag> optionalTag = tagRepository.findByTagName(tag.getTagName());
        if (optionalTag.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.TAG_EXISTS);
        }
        return tagRepository.save(tag);
    }
}
