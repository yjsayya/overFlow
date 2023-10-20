package com.example.overflow.controller;

import com.example.overflow.dto.request.QuestionPatchDto;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.MultiResponseDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Question;
import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.service.QuestionService;
import com.example.overflow.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final static String QUESTION_URL = "questions";
    private final QuestionService questionService;
    private final QuestionMapper mapper;

//    @PostMapping("/{memberId}")
//    public ResponseEntity postQuestion(@PathVariable("memberId") @Positive Integer memberId,
//                                       @Valid @RequestBody QuestionPostDto requestBody) {
//        Question question = mapper.questionPostDtoToQuestion(requestBody);
//        List<String> tagNames = requestBody.getTagNames();
//        Question createdQuestion = questionService.createQuestion(memberId, question, tagNames);
//
//        QuestionResponseDto response = mapper.questionToResponseDto(createdQuestion);
//        response.setTagNames(tagNames);
//
//        URI location = UriCreator.createUri(QUESTION_URL, createdQuestion.getQuestionId());
//        return ResponseEntity.created(location).body(response);
//    }

    @PostMapping("/{memberId}")
    public ResponseEntity<QuestionResponseDto> postQuestion(@PathVariable("memberId") @Positive Integer memberId,
                                                            @Valid @RequestBody QuestionPostDto request) {
        QuestionResponseDto response = questionService.createQuestion(memberId, request);

        URI location = UriCreator.createUri(QUESTION_URL, response.getQuestionId());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity patchQuestion(@PathVariable("memberId") Integer memberId,
                                        @Valid @RequestBody QuestionPatchDto requestBody) {
        Question question = mapper.questionPatchDtoToQuestion(requestBody);
        Question updateQuestion = questionService.updateQuestion(question, memberId);

        return new ResponseEntity<>(mapper.questionToResponseDto(updateQuestion), HttpStatus.OK);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") @Positive Integer questionId) {
        Question question = questionService.findQuestion(questionId);

        return new ResponseEntity<>(mapper.questionToResponseDto(question), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "newest") String order) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size, order);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.questionListToResponseDtos(questions), pageQuestions), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") @Positive Integer questionId,
                                            @RequestParam("memberId") @Positive Integer memberId) {
        questionService.deleteQuestion(questionId, memberId);

        return ResponseEntity.ok().build();
    }


}