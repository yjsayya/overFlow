package com.example.overflow.dto.response;

import com.example.overflow.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagResponseDto {

    private Integer id;
    private String tagName;
    private String content;
    private Integer tagMentionCount;

}
