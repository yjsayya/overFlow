package com.example.overflow.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    // Member
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    INVALID_PASSWORD(401, "Password is Invalid"),
    INVALID_(401, "You are not authorized"),

    // Question
    QUESTION_NOT_FOUND(404, "Question not found"),
    CANNOT_CHANGE_QUESTION(403, "Question can not change"),
    CANNOT_READ_QUESTION(403, "Question can not read"),
    QUESTION_USER_NOT_MATCH(403, "Question and user don't match"),

    // Answer
    ANSWER_NOT_FOUND(404, "Answer not found"),
    ANSWER_FILTER_NOT_FOUND(404, "Answer filter not found"),
    CANNOT_UPDATE_ANSWER(403, "Answer can not be changed"),
    ANSWER_ALREADY_ACCEPTED(404, "Answer already accepted"),

    // Tag
    TAG_NOT_FOUND(404, "Tag not found"),
    TAG_EXISTS(409, "Tag exists"),
    UN_AUTHORIZED(404, "Only the author can delete"),
    VOTE_NOT_FOUNT(404,"Vote not found"),
    ALREADY_EXIST_VOTE(404,"Already exist vote"),

    // Comment
    COMMENT_NOT_FOUND(404, "Comment not found"),


    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int status;
    private final String message;


}