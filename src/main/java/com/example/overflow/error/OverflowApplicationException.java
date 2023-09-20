package com.example.overflow.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OverflowApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;


}