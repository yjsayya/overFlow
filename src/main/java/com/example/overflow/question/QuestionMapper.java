package com.example.overflow.question;

import java.util.List;

public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post postQuestion);
    Question questionPatchDtoToQuestion(QuestionDto.Patch patchQuestion);
    QuestionDto.Response questionToResponseDto(Question question);
    List<QuestionDto.Response> questionsToResponseDtos(List<Question> questions);

}
