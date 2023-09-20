package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.entity.Member;
import com.example.overflow.entity.Question;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public Question createQuestion(Integer member_id, Question question, List<String> tagNames) {
        Optional<Member> optionalMember = memberRepository.findById(member_id);
        if (optionalMember.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        question.setMember(optionalMember.get());

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, Integer memberId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        if (!findQuestion.getMember().getId().equals(memberId)){
            throw new BusinessLogicException(ExceptionCode.QUESTION_USER_NOT_MATCH);
        }

        Optional.ofNullable(question.getTitle())
                .ifPresent(questionTitle -> findQuestion.setTitle(questionTitle));
        //질문 문제 수정
        Optional.ofNullable(question.getContent())
                .ifPresent(questionContent -> findQuestion.setContent(questionContent));

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(Integer questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setQuestionViews((question.getQuestionViews() +1)); // 조회수 +1
        return question;
    }

    public void deleteQuestion(Integer questionId, Integer memberId) {
        Question question = findVerifiedQuestion(questionId);
        Member member = memberService.findMemberById(memberId);

        if (!question.getMember().equals(member)) {
            throw new BusinessLogicException(ExceptionCode.UN_AUTHORIZED);
        }

        questionRepository.delete(question);
    }

    //해당 게시글이 존재하는지 체크
    public Question findVerifiedQuestion(Integer id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }
        return optionalQuestion.get();
    }

}
