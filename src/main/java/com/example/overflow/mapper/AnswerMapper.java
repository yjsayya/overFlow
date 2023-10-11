package com.example.overflow.mapper;

import com.example.overflow.dto.request.AnswerCreateRequest;
import com.example.overflow.dto.request.AnswerUpdateRequest;
import com.example.overflow.dto.response.AnswerCreateResponse;
import com.example.overflow.dto.response.AnswerUpdateResponse;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Answer;
import com.example.overflow.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    Answer answerCreateRequestToAnswer(AnswerCreateRequest answerCreateRequest);
    Answer answerUpdateRequestToAnswer(AnswerUpdateRequest answerUpdateRequest);
    AnswerCreateResponse answerToCreateResponse(Answer answer);

    AnswerUpdateResponse answerToUpdateResponse(Answer answer);
    List<AnswerCreateResponse> answerToResponse(List<Answer> answerList);

}