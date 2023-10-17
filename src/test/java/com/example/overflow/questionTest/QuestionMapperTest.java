package com.example.overflow.questionTest;

import com.example.overflow.dto.request.QuestionPatchDto;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Question;
import com.example.overflow.mapper.QuestionMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionMapperTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Autowired
    private QuestionMapper mapper;

    @Test
    public void testQuestionPostDtoToQuestion() {
        // Create a sample QuestionPostDto
        QuestionPostDto postDto = new QuestionPostDto();
        postDto.setTitle("Sample Title");
        postDto.setContent("Sample Content");
        postDto.setTagNames(List.of("Java", "Spring"));

        // Map the QuestionPostDto to Question using the mapper
        Question question = mapper.questionPostDtoToQuestion(postDto);

        // Verify that the mapping is correct
        assertEquals(postDto.getTitle(), question.getTitle());
        assertEquals(postDto.getContent(), question.getContent());
        // You can add more assertions for other properties if needed
    }
    @Test
    public void testQuestionPatchDtoToQuestion() {
        // Create a sample QuestionPatchDto
        QuestionPatchDto patchDto = new QuestionPatchDto();
        patchDto.setTitle("Updated Title");
        patchDto.setContent("Updated Content");

        // Map the QuestionPatchDto to Question using the mapper
        Question question = mapper.questionPatchDtoToQuestion(patchDto);

        // Verify that the mapping is correct
        assertEquals(patchDto.getTitle(), question.getTitle());
        assertEquals(patchDto.getContent(), question.getContent());
        // You can add more assertions for other properties if needed
    }

    @Test
    public void testQuestionToResponseDto() {
        // Create a sample Question
        Question question = new Question();
        question.setTitle("Sample Title");
        question.setContent("Sample Content");

        // Map the Question to QuestionResponseDto using the mapper
        QuestionResponseDto responseDto = mapper.questionToResponseDto(question);

        // Verify that the mapping is correct
        assertEquals(question.getTitle(), responseDto.getTitle());
        assertEquals(question.getContent(), responseDto.getContent());
        // You can add more assertions for other properties if needed
    }

}