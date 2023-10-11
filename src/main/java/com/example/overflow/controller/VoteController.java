package com.example.overflow.controller;

import com.example.overflow.dto.request.VoteForAnswerPostDto;
import com.example.overflow.dto.request.VoteForQuestionPostDto;
import com.example.overflow.dto.response.VoteResponseDto;
import com.example.overflow.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final static String VOTE_URL = "/vote";
    private final VoteService voteService;

    @PostMapping("/question")
    public ResponseEntity postQuestionVote(@Valid @RequestBody VoteForQuestionPostDto requestBody) {
        VoteResponseDto response = voteService.voteForQuestion(requestBody);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/answer")
//    public ResponseEntity postQuestionVote(@Valid @RequestBody VoteForAnswerPostDto requestBody) {
//        VoteResponseDto response = voteService.voteForAnswer(requestBody);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
