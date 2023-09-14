package com.example.overflow.question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final static String QUESTION_URL = "questions";
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/{memberId}")
    public ResponseEntity postQuestion(@PathVariable("memberId") @Positive Integer memberId,
                                       @Valid @RequestBody QuestionDto.Post requestBody) {
        Question question = QuestionDto.Post.dtoToQuestion(requestBody);
        List<String> tagNames = requestBody.getTagNames();

        Question createdQuestion = questionService.createQuestion(memberId, question, tagNames);
        return new ResponseEntity<>(QuestionDto.Response.response(question), HttpStatus.CREATED);
    }





}
