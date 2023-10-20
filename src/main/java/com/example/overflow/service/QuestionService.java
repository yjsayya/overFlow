package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.dto.request.QuestionPostDto;
import com.example.overflow.dto.response.QuestionResponseDto;
import com.example.overflow.entity.Member;
import com.example.overflow.entity.Question;
import com.example.overflow.entity.Tag;
import com.example.overflow.entity.TagOnQuestion;
import com.example.overflow.mapper.QuestionMapper;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.repository.QuestionRepository;
import com.example.overflow.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final QuestionMapper mapper;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;

//    public Question createQuestion(Integer memberId, Question question, List<String> tagNames) {
//        // 맴버가 존재하는지 검사하고 없으면 에러
//        Optional<Member> optionalMember = memberRepository.findById(memberId);
//        if (optionalMember.isEmpty()) {
//            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
//        }
//        question.setMember(optionalMember.get());
//
//        // 태그를 검사하고 매핑
//        List<Tag> tags = new ArrayList<>();
//
//        for (String tagName : tagNames) {
//            Optional<Tag> optionalTag = tagRepository.findByTagName(tagName);
//            if (optionalTag.isPresent()) {
//                Tag tag = optionalTag.get();
//                tag.setTagMentionCount(tag.getTagMentionCount() + 1);
//                tagRepository.save(tag);
//                tags.add(tag);
//            } else {
//                throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);
//            }
//        }
//
//        // 질문과 연관된 태그 목록을 설정합니다.
//        List<TagOnQuestion> tagOnQuestions = new ArrayList<>();
//        for (Tag tag : tags) {
//            TagOnQuestion tagOnQuestion = new TagOnQuestion();
//            tagOnQuestion.setTag(tag);
//            tagOnQuestion.setQuestion(question);
//            tagOnQuestions.add(tagOnQuestion);
//        }
//        question.setTagOnQuestions(tagOnQuestions);
//
//        return questionRepository.save(question);
//    }

    public QuestionResponseDto createQuestion(Integer memberId, QuestionPostDto request) {
        // 1. 맴버가 존재하는지 검사하고 없으면 에러
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        Question question = mapper.questionPostDtoToQuestion(request);
        question.setMember(optionalMember.get());

        // 2. 태그를 검사하고 매핑
        List<Tag> tags = new ArrayList<>();

        for (String tagName : request.getTagNames()) {
            Optional<Tag> optionalTag = tagRepository.findByTagName(tagName);
            if (optionalTag.isPresent()) {
                Tag tag = optionalTag.get();
                tag.setTagMentionCount(tag.getTagMentionCount() + 1);
                tagRepository.save(tag);
                tags.add(tag);
            } else {
                throw new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND);
            }
        }

        // 3. 질문과 연관된 태그 목록을 설정합니다.
        List<TagOnQuestion> tagOnQuestions = new ArrayList<>();
        for (Tag tag : tags) {
            TagOnQuestion tagOnQuestion = new TagOnQuestion();
            tagOnQuestion.setTag(tag);
            tagOnQuestion.setQuestion(question);
            tagOnQuestions.add(tagOnQuestion);
        }
        question.setTagOnQuestions(tagOnQuestions);

        // 4. 질문을 저장하고 Response DTO로 변환
        Question createdQuestion = questionRepository.save(question);
        QuestionResponseDto response = mapper.questionToResponseDto(createdQuestion);
        response.setTagNames(request.getTagNames());

        return response;
    }



    public Question updateQuestion(Question question, Integer memberId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        if (!findQuestion.getMember().getId().equals(memberId)){
            throw new BusinessLogicException(ExceptionCode.QUESTION_USER_NOT_MATCH);
        }

        Optional.ofNullable(question.getTitle())
                .ifPresent(questionTitle -> findQuestion.setTitle(questionTitle));
        Optional.ofNullable(question.getContent())
                .ifPresent(questionContent -> findQuestion.setContent(questionContent));

        return questionRepository.save(findQuestion);
    }

    //해당 게시글이 존재하는지 체크
    public Question findVerifiedQuestion(Integer id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }
        return optionalQuestion.get();
    }

    @Transactional(readOnly = true)
    public Question findQuestion(Integer questionId) {
        Question question = findVerifiedQuestion(questionId);
        question.setQuestionViews((question.getQuestionViews() +1)); // 조회수 +1
        return question;
    }

    @Transactional(readOnly = true)
    public Page<Question> findQuestions(int page, int size, String order) {
        Pageable pageable;
        if (order.equals("newest")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        } else if (order.equals("answered")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "answerCount"));
        } else if (order.equals("unanswered")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        } else if (order.equals("voteCount")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "questionVotes"));
        } else {
            throw new IllegalArgumentException("Invalid sort parameter");
        }

        Page<Question> questionPage;
        if (order.equals("unanswered")) {
            questionPage = questionRepository.findAllByAnswerCount(0, pageable);
        } else if (order.equals("answered")) {
            questionPage = questionRepository.findAllQuestionsAnswered(pageable);
        } else if (order.equals("voteCount")) {
            questionPage = questionRepository.findAllByOrderByQuestionVotesDesc(pageable);
        }else {
            questionPage = questionRepository.findAll(pageable);
        }
        return questionPage;
    }

    public void deleteQuestion(Integer questionId, Integer memberId) {
        Question question = findVerifiedQuestion(questionId);
        Member member = memberService.findMemberById(memberId);

        if (!question.getMember().equals(member)) {
            throw new BusinessLogicException(ExceptionCode.UN_AUTHORIZED);
        }

        questionRepository.delete(question);
    }

}
