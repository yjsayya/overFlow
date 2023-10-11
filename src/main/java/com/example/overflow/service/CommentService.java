package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.entity.Comment;
import com.example.overflow.entity.Member;
import com.example.overflow.repository.CommentRepository;
import com.example.overflow.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    /**
     * 댓글 작성하기
     */
    public void create(String content) {

        Comment comment = commentRepository.save(Comment.of(content));

    }

    /**
     * 댓글 수정하기
     */
    public void update(Integer id,String content) {
        // 에러 체크
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        comment.setContent(content);

    }

    /**
     * 댓글 삭제하기
     */
    public void delete(Integer id, String content) {
        // 에러 체크
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }


    /**
     * 댓글 가져오기 - by memberId
     */
    public List<Comment> getCommentsByMemberId(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member.getCommentList();
    }

    /**
     * 댓글 가져오기 - by AnswerId
     */
//    public List<Comment> getCommentsByAnswerId(Integer answerId) {
//        Answer answer =
//    }


}