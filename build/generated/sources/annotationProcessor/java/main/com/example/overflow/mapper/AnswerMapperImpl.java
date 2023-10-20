package com.example.overflow.mapper;

import com.example.overflow.dto.request.AnswerCreateRequest;
import com.example.overflow.dto.request.AnswerUpdateRequest;
import com.example.overflow.dto.response.AnswerCreateResponse;
import com.example.overflow.dto.response.AnswerUpdateResponse;
import com.example.overflow.entity.Answer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-20T12:15:18+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerCreateRequestToAnswer(AnswerCreateRequest answerCreateRequest) {
        if ( answerCreateRequest == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerCreateRequest.getContent() );

        return answer;
    }

    @Override
    public Answer answerUpdateRequestToAnswer(AnswerUpdateRequest answerUpdateRequest) {
        if ( answerUpdateRequest == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( answerUpdateRequest.getContent() );

        return answer;
    }

    @Override
    public AnswerCreateResponse answerToCreateResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerCreateResponse answerCreateResponse = new AnswerCreateResponse();

        answerCreateResponse.setContent( answer.getContent() );
        answerCreateResponse.setCreatedAt( answer.getCreatedAt() );
        answerCreateResponse.setUpdatedAt( answer.getUpdatedAt() );

        return answerCreateResponse;
    }

    @Override
    public AnswerUpdateResponse answerToUpdateResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerUpdateResponse answerUpdateResponse = new AnswerUpdateResponse();

        return answerUpdateResponse;
    }

    @Override
    public List<AnswerCreateResponse> answerToResponse(List<Answer> answerList) {
        if ( answerList == null ) {
            return null;
        }

        List<AnswerCreateResponse> list = new ArrayList<AnswerCreateResponse>( answerList.size() );
        for ( Answer answer : answerList ) {
            list.add( answerToCreateResponse( answer ) );
        }

        return list;
    }
}
