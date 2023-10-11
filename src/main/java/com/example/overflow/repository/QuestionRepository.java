package com.example.overflow.repository;

import com.example.overflow.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findById(Integer Id);

    Page<Question> findAllByAnswerCount(int answerCount, Pageable pageable);

    Page<Question> findAllByOrderByQuestionVotesDesc(Pageable pageable);

    @Query(value = "select q from Question q where size(q.answerList) > 0")
    Page<Question> findAllQuestionsAnswered(Pageable pageable);

}
