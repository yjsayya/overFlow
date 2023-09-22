package com.example.overflow.questionTest;

import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.entity.Member;
import com.example.overflow.entity.Question;
import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.service.QuestionService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.*;
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
    private QuestionMapper questionMapper;
    @MockBean
    private QuestionService questionService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void postQuestionTest() throws Exception {
        // Given
        QuestionPostDto postDto = new QuestionPostDto("Sample Title", "Sample Content", Collections.singletonList("Java"));

        // When
        mockMvc.perform(
                        post("/questions/{memberId}", 1) // Member ID를 사용합니다.
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(gson.toJson(postDto))
                )
                // Then
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", startsWith("/questions/")));
    }



}