package com.example.overflow.repository;

import com.example.overflow.entity.Answer;
import com.example.overflow.entity.Member;
import com.example.overflow.entity.Question;
import com.example.overflow.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Vote findByMemberAndQuestion(Member member, Question question);
    Vote findByMemberAndAnswer(Member member, Answer answer);

}
