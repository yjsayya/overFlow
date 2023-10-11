package com.example.overflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    /**
     * void인 경우
     */
    public static <T> Response<T> success() {
        return new Response<>("SUCCESS", null);
    }

    /**
     * json 반환
     */
    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    }

    /**
     * 실패
     */
    public static Response<Void> error(String resultCode) {
        return new Response<>(resultCode, null);
    }


}
