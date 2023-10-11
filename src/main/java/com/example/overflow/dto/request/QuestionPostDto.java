package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionPostDto {

    @NotBlank @Size(min = 10, max = 255)
    private String title;

    @NotBlank @Size(min = 10, max = 10000)
    private String content;

    private List<String> tagNames;

}
