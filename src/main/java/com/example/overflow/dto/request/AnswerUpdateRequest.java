package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerUpdateRequest {

    private Integer questionId;
    private String content;

}