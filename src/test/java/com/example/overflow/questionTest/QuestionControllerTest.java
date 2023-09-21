package com.example.overflow.questionTest;

import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.entity.Question;
import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void postQuestionTest() throws Exception {
        QuestionPostDto requestDto = new QuestionPostDto();
        requestDto.setTitle("Sample Title");
        requestDto.setContent("Sample Content");
        requestDto.setTagNames(Collections.singletonList("Java"));

        // Mock QuestionService's createQuestion method to return a mock Question object
        when(questionService.createQuestion(anyInt(), any(Question.class), anyList()))
                .thenReturn(new Question());

        // when
        ResultActions result = mockMvc.perform(
                post("/questions/{memberId}", 1) // Replace 1 with the desired member ID
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(requestDto)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", startsWith("/questions/")));
    }


}