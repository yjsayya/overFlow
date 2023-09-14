package com.example.overflow.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class QuestionDto {


    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private String content;
        private List<String> tagNames;

        public static Question dtoToQuestion(Post requestBody) {
            return Question.builder()
                    .title(requestBody.getTitle())
                    .content(requestBody.getContent())
                    .build();
        }
    }

    public class Patch {

    }


    @Builder
    @Getter
    public static class Response {
        private Integer questionId;
        private String title;
        private String content;

        public static Response response(Question updateQuestion) {
            return Response.builder()
                    .questionId(updateQuestion.getQuestionId())
                    .title(updateQuestion.getTitle())
                    .content(updateQuestion.getContent())
                    .build();
        }



    }

}
