package com.example.overflow.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }


}