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
public class VoteForAnswerPostDto {

    @NotNull
    private Integer memberId;

    @NotNull
    private Integer answerId;

    @NotNull
    private int upvote;
}
