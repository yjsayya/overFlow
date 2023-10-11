package com.example.overflow.dto.response;

import com.example.overflow.entity.Question;
import com.example.overflow.entity.Tag;
import com.example.overflow.entity.TagOnQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {

    private Integer questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private List<String> tagNames;
    private int questionViews;
    private int questionVotes;
    private int answerCount;


    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

}
