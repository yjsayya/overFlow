package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequestDto {

    private String email;
    private String password;
    private String userName;
    private String phone;

}