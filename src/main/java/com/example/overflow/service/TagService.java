package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.entity.Tag;
import com.example.overflow.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Tag updateTag(Tag tag) {
        Tag findTag = findVerifiedTag(tag.getId());

        Optional.ofNullable(tag.getTagName())
                .ifPresent(tagName -> findTag.setTagName(tagName));
        Optional.ofNullable(tag.getContent())
                .ifPresent(content -> findTag.setContent(content));

        return tagRepository.save(findTag);
    }

    //해당테그가 존재하는지 체크
    public Tag findVerifiedTag(Integer id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if(optionalTag.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);
        }
        return optionalTag.get();
    }

    @Transactional(readOnly = true)
    public Tag findTag(Integer id) {
        Tag tag = findVerifiedTag(id);
        return tag;
    }

    @Transactional(readOnly = true)
    public Page<Tag> findTags(int page, int size, String order) {
        Pageable pageable;
        if (order.equals("popular")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "tagMentionCount"));
        } else if (order.equals("name")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "tagName"));
        } else if (order.equals("new")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        } else {
            throw new IllegalArgumentException("Invalid sort parameter");
        }

        return tagRepository.findAll(pageable);
    }

}
