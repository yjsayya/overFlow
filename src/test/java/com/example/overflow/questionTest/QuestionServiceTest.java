package com.example.overflow.questionTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class QuestionServiceTest {

    private QuestionService questionService;
    private QuestionPort questionPort;
    private QuestionRepository questionRepository;

    @BeforeEach
    void setup() {
        questionRepository = new QuestionRepository();
        questionPort = new QuestionAdapter(questionRepository);
        questionService = new QuestionService(questionPort);

    }

    @Test
    void 질문등록() {
        final String title = "파이썬과 자바의 차이점을 알려주세요";
        final String content = "제목이 곧 내용입니다.";

        questionService.addQuestion(title, content);
    }

    private class QuestionService {
        private final QuestionPort questionPort;

        private QuestionService(QuestionPort questionPort) {
            this.questionPort = questionPort;
        }

        public void addQuestion(final String title, final String content) {
            final Question question = new Question(title, content);

            questionPort.save(question);
        }
    }

    private class Question {
        private Integer questionId;
        private final String title;
        private final String  content;

        public Question(final String title, final String content) {
            Assert.hasText(content,"제목은 필수입니다.");
            Assert.isTrue(content.length() > 10, "내용은 10자 이상이여야 합니다.");
            this.title = title;
            this.content = content;
        }

        public void assignId(final Integer questionId) {
            this.questionId = questionId;
        }

        public Integer getQuestionId() {
            return questionId;
        }

    }

    private class AddQuestionRequest {
        public AddQuestionRequest(final String title, final String content) {
            Assert.hasText(content,"제목은 필수입니다.");
            Assert.isTrue(content.length() > 10, "내용은 10자 이상이여야 합니다.");
        }
    }

    private interface QuestionPort {
        void save(final Question question);
    }

    public class QuestionAdapter implements QuestionPort {
        private final QuestionServiceTest.QuestionRepository questionRepository;

        public QuestionAdapter(QuestionServiceTest.QuestionRepository questionRepository) {
            this.questionRepository = questionRepository;
        }

        @Override
        public void save(final Question question) {
            questionRepository.save(question);
        }
    }

    private class QuestionRepository {
        private Map<Integer, Question> persistence = new HashMap<>();
        private Integer sequence = 0;

        public void save(final Question question) {
            question.assignId(++sequence);
            persistence.put(question.getQuestionId(), question);
        }

    }

}
