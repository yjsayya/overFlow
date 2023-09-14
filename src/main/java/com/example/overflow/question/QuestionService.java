package com.example.overflow.question;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Integer memberId, Question question, List<String> tagNames) {
        return questionRepository.save(question);
    }
}
