package com.example.overflow.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class QuestionPostDto {
    @NotBlank
    private String title;
    private String content;
    private List<String> tagNames;
}
