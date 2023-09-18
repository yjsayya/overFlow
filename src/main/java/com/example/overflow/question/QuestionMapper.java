package com.example.overflow.question;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post postQuestion);
    Question questionPatchDtoToQuestion(QuestionDto.Patch patchQuestion);
    QuestionDto.Response questionToResponseDto(Question question);
    List<QuestionDto.Response> questionsToResponseDtos(List<Question> questions);

}
