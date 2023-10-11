package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.dto.request.AnswerCreateRequest;
import com.example.overflow.dto.request.AnswerUpdateRequest;
import com.example.overflow.dto.response.AnswerCreateResponse;
import com.example.overflow.dto.response.AnswerUpdateResponse;
import com.example.overflow.entity.Answer;
import com.example.overflow.entity.Question;
import com.example.overflow.entity.Tag;
import com.example.overflow.entity.TagOnAnswer;
import com.example.overflow.mapper.AnswerMapper;
import com.example.overflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private MemberRepository memberRepository;
    private TagRepository tagRepository;
    private TagOnAnswerRepository tagOnAnswerRepository;


    private AnswerMapper answerMapper;


    /**
     * 답글 생성
     */
    @Transactional
    public AnswerCreateResponse createAnswer(AnswerCreateRequest request) {
        // 1. Question 찾기
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        Answer answer = answerMapper.answerCreateRequestToAnswer(request);
        // 2. Answer 설정 및 저장
        answer.setQuestion(question);
        answer = answerRepository.save(answer);

        // 3. 태그 검사 및 TagOnAnswer 설정
        for (String tagName : request.getTags()) {
            Optional<Tag> optionalTag = tagRepository.findByTagName(tagName);
            Tag tag;
            if (optionalTag.isPresent())
                tag = optionalTag.get();
            else
                throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);

            TagOnAnswer tagOnAnswer = new TagOnAnswer();
            tagOnAnswer.setAnswer(answer);
            tagOnAnswer.setTag(tag);

            tagOnAnswerRepository.save(tagOnAnswer);
        }

        return answerMapper.answerToCreateResponse(answer);
    }

    /**
     * 답글 수정
     */
    public AnswerUpdateResponse updateAnswer(Integer questionId, AnswerUpdateRequest request) {
        // answer 찾기
        Answer entity = answerRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND)
        );

        // answer 수정 진행시켜
        entity.setContent(request.getContent());
        return answerMapper.answerToUpdateResponse(entity);
    }

    /**
     * 답글 삭제
     */
    public void deleteAnswer(Integer questionId) {
        // answer 찾기
        Answer entity = answerRepository.findById(questionId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        // 삭제 진행시켜
        answerRepository.delete(entity);
    }

    public long getAnswerCount() {
        return answerRepository.count();
    }


}
