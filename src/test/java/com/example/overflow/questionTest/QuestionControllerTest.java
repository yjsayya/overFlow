package com.example.overflow.questionTest;

import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.service.QuestionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



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
    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    public void postQuestionTest() throws Exception {
//        QuestionPostDto requestDto = new QuestionPostDto();
//        requestDto.setTitle("Sample Title");
//        requestDto.setContent("Sample Content");
//        requestDto.setTagNames(Collections.singletonList("Java"));
//
//        given(questionService.createQuestion(any(Question.class))).willReturn(Question.class);
//
//        String content = gson.toJson(requestBody);
//        // Mock QuestionService's createQuestion method to return a mock Question object
//        when(questionService.createQuestion(anyInt(), any(Question.class), anyList()))
//                .thenReturn(new Question());
//
//        // when
//        ResultActions result = mockMvc.perform(
//                post("/questions/{memberId}", 1) // Replace 1 with the desired member ID
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(gson.toJson(requestDto)));
//
//        // then
//        result.andExpect(status().isCreated())
//                .andExpect(header().string("Location", startsWith("/questions/")));
//    }
//    @Test
//    public void postQuestionTest() throws Exception {
//        // Given
//        QuestionPostDto postDto = new QuestionPostDto("Sample Title", "Sample Content", Collections.singletonList("Java"));
//
//        // When
//        mockMvc.perform(
//                        post("/questions/{memberId}", 1) // Member ID를 사용합니다.
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(gson.toJson(postDto))
//                )
//                // Then
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", startsWith("/questions/")));
//    }



}