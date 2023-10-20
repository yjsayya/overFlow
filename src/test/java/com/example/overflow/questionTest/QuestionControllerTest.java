package com.example.overflow.questionTest;

import com.example.overflow.controller.QuestionController;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Question;
import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.service.QuestionService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private QuestionMapper mapper;

    @MockBean
    private QuestionService questionService;

    private final static String QUESTION_URL = "questions";

//    @Test
//    void 질문등록_테스트() throws Exception {
//        // Mock 데이터 생성
//        QuestionPostDto requestDto = new QuestionPostDto();
//        requestDto.setTitle("Sample Title");
//        requestDto.setContent("Sample Content");
//        requestDto.setTagNames(List.of("java", "python"));
//
//        Question question = new Question();
//        question.setTitle("Sample Title");
//        question.setContent("Sample Content");
//        question.setQuestionId(1); // Replace with the expected ID value
//
//        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
//        questionResponseDto.setQuestionId(1);
//        questionResponseDto.setTitle("Sample Title");
//        questionResponseDto.setContent("Sample Content");
//        questionResponseDto.setTagNames(Arrays.asList("java", "python"));
//
//
//        when(questionService.createQuestion(1, question, requestDto.getTagNames()))
//                .thenReturn(question);
//
//        when(mapper.questionPostDtoToQuestion(requestDto))
//                .thenReturn(question);
//
//        when(questionService.createQuestion(1, question, requestDto.getTagNames()))
//                .thenReturn(question);
//
//        when(mapper.questionPostDtoToQuestion(requestDto))
//                .thenReturn(question);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/questions/{memberId}", 1)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(requestDto))
//                )
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }

}