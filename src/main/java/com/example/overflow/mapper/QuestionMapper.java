package com.example.overflow.mapper;

import com.example.overflow.dto.request.QuestionPatchDto;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionPatchDto requestBody);
    QuestionResponseDto questionToResponseDto(Question question);
    List<QuestionResponseDto> questionListToResponseDtos(List<Question> questionList);


}