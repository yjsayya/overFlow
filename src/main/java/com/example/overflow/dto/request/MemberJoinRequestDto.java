package com.example.overflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberJoinRequestDto {

    @Email
    private String email;

    @Pattern(regexp = "^\\d{8,16}$")
    private String password;

    private String userName;

    @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$\n")
    private String phone;

}