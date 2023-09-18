package com.example.overflow.questionTest;


import com.example.overflow.question.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.google.gson.Gson;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
        // given
        QuestionDto.Post requestBody = new QuestionDto.Post("Sample Title", "Sample Content", Collections.singletonList("Tag1"));
        Question question = mapper.questionPostDtoToQuestion(requestBody);
        question.setQuestionId(1);

        given(questionService.createQuestion(any(Question.class))).willReturn(question);

        String content = gson.toJson(requestBody);

        // when
        ResultActions actions = mockMvc.perform(
                post("/questions/{memberId}", 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("questions/1"))));
    }


}