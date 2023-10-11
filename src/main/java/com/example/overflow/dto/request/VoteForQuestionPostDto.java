package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteForQuestionPostDto {

    @NotNull
    private Integer memberId;

    @NotNull
    private Integer questionId;

    @NotNull
    private int upvote;

}
