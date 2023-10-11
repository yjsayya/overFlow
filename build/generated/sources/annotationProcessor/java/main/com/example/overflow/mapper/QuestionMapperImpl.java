package com.example.overflow.mapper;

import com.example.overflow.dto.request.QuestionPatchDto;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-21T14:28:21+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Amazon.com Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPostDto.getTitle() );
        question.setContent( questionPostDto.getContent() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionPatchDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( requestBody.getQuestionId() );
        question.setTitle( requestBody.getTitle() );
        question.setContent( requestBody.getContent() );

        return question;
    }

    @Override
    public QuestionResponseDto questionToResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        questionResponseDto.setQuestionId( question.getQuestionId() );
        questionResponseDto.setTitle( question.getTitle() );
        questionResponseDto.setContent( question.getContent() );
        questionResponseDto.setCreatedAt( question.getCreatedAt() );
        questionResponseDto.setUpdatedAt( question.getUpdatedAt() );
        questionResponseDto.setQuestionViews( question.getQuestionViews() );
        questionResponseDto.setQuestionVotes( question.getQuestionVotes() );
        questionResponseDto.setAnswerCount( question.getAnswerCount() );

        return questionResponseDto;
    }

    @Override
    public List<QuestionResponseDto> questionListToResponseDtos(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionResponseDto> list = new ArrayList<QuestionResponseDto>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( questionToResponseDto( question ) );
        }

        return list;
    }
}