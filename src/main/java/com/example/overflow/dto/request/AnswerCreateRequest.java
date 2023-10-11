package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AnswerCreateRequest {

    private Integer questionId;
    private String content;
    private List<String> tags;

}