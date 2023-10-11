package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagPostDto {

    @NotBlank
    private String tagName;
    private String content;
    private Integer tagMentionCount = 0;

}