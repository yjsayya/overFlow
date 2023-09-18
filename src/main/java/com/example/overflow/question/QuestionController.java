package com.example.overflow.question;

import com.example.overflow.utils.UriCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final static String QUESTION_URL = "questions";
    private QuestionService questionService;
    private QuestionMapper mapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @PostMapping("/{memberId}")
    public ResponseEntity postQuestion(@PathVariable("memberId") @Positive Integer memberId,
                                       @Valid @RequestBody QuestionDto.Post requestBody) {
        Question question = mapper.questionPostDtoToQuestion(requestBody);
        List<String> tagNames = requestBody.getTagNames();
        Question createdQuestion = questionService.createQuestion(question);

        QuestionDto.Response response = mapper.questionToResponseDto(createdQuestion);

        URI location = UriCreator.createUri(QUESTION_URL, createdQuestion.getQuestionId());
        return ResponseEntity.created(location).body(response);
    }





}
