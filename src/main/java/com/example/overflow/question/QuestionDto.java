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

    }

    public class Patch {

    }


    @Getter
    @AllArgsConstructor
    public static class Response {
        private Integer questionId;
        private String title;
        private String content;
    }


}