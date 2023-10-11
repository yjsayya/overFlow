package com.example.overflow.controller;

import com.example.overflow.dto.Response;
import com.example.overflow.dto.request.AnswerCreateRequest;
import com.example.overflow.dto.request.AnswerUpdateRequest;
import com.example.overflow.dto.response.AnswerCreateResponse;
import com.example.overflow.dto.response.AnswerUpdateResponse;
import com.example.overflow.mapper.AnswerMapper;
import com.example.overflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private AnswerService answerService;
    private AnswerMapper answerMapper;

    @PostMapping
    public ResponseEntity<AnswerCreateResponse> saveAnswer(AnswerCreateRequest request) {
        AnswerCreateResponse response = answerService.createAnswer(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{question_id}")
    public Response<AnswerUpdateResponse> updateAnswer(
            @RequestBody AnswerUpdateRequest request,
            @PathVariable final Integer question_id
    ) {
        AnswerUpdateResponse response = answerService.updateAnswer(question_id, request);
        return Response.success(response);
    }

    @DeleteMapping("/{question_id}")
    public Response<Void> deleteAnswer(@PathVariable Integer question_id) {
        answerService.deleteAnswer(question_id);
        return Response.success();
    }


}