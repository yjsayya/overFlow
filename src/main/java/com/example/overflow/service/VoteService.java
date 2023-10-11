package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.dto.request.VoteForQuestionPostDto;
import com.example.overflow.dto.response.VoteResponseDto;
import com.example.overflow.entity.Member;
import com.example.overflow.entity.Question;
import com.example.overflow.entity.Vote;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.repository.QuestionRepository;
import com.example.overflow.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final MemberRepository memberRepository;
    private final VoteRepository voteRepository;
    private final QuestionRepository questionRepository;

    public VoteResponseDto voteForQuestion(VoteForQuestionPostDto requestBody) {
        // 맴버가 존재하는지 검사하고 없으면 에러
        Optional<Member> optionalMember = memberRepository.findById(requestBody.getMemberId());
        if (optionalMember.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        Member member = optionalMember.get();

        // 질문이 존재하는지 검사하고 없으면 에러
        Optional<Question> optionalQuestion = questionRepository.findById(requestBody.getQuestionId());
        if (optionalQuestion.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND);
        }
        Question question = optionalQuestion.get();

        // 회원이 해당 질문에 이미 투표한 경우
        Vote existingVote = voteRepository.findByMemberAndQuestion(member, question);

        // 새로운 투표 방향을 가져옵니다.
        int newUpvote = requestBody.getUpvote();


        if (existingVote != null) {
            // 이미 투표가 있는 경우
            if (existingVote.getUpvote() == newUpvote) {
                // 같은 방향으로 투표할 때 예외 처리
                throw new BusinessLogicException(ExceptionCode.ALREADY_EXIST_VOTE);
            } else {
                // 투표 방향이 변경된 경우, 이전 투표를 삭제합니다.
                voteRepository.delete(existingVote);

                // 질문의 투표 수 업데이트
                int voteChange = existingVote.getUpvote() == 1 ? -1 : 1;
                question.setQuestionVotes(question.getQuestionVotes() + voteChange);
            }
        } else {
            // 이전 투표가 없는 경우, 새로운 투표를 생성합니다.
            Vote newVote = new Vote();
            newVote.setMember(member);
            newVote.setQuestion(question);
            newVote.setUpvote(newUpvote);
            voteRepository.save(newVote);

            // 질문의 투표 수 업데이트
            question.setQuestionVotes(question.getQuestionVotes() + newUpvote);
        }

        questionRepository.save(question);

        // VoteResponseDto 생성
        VoteResponseDto response = new VoteResponseDto();
        response.setVoteCount(question.getQuestionVotes());

        return response;
    }

}
