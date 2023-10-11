package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class TagPatchDto {

    @NotNull
    private Integer id;

    @NotBlank @Size(max = 50)
    private String tagName;

    private String content;

    private Integer tagMentionCount;
}
