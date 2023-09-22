package com.example.overflow.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {

    @NotNull
    private Integer questionId;

    @NotBlank
    private String title;

    private String content;
    private List<String> tagNames;


}