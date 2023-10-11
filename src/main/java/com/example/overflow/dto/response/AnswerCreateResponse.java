package com.example.overflow.dto.response;

import com.example.overflow.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {

    private Integer answerId;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Tag> tags;
    private int answerViews;
    private int answerVotes;
    private int commentCount;


}