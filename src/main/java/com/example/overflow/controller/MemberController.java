package com.example.overflow.controller;

import com.example.overflow.dto.request.MemberJoinRequestDto;
import com.example.overflow.dto.request.MemberLoginRequestDto;
import com.example.overflow.dto.response.MemberJoinResponseDto;
import com.example.overflow.dto.response.MemberLoginResponseDto;
import com.example.overflow.entity.Member;
import com.example.overflow.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto dto) {
        Member member = memberService.join(dto.getEmail(), dto.getPassword(), dto.getUserName(), dto.getPhone());
        return MemberJoinResponseDto.entityToDto(member);
    }

    @PostMapping("/login")
    public MemberLoginResponseDto login(@RequestBody MemberLoginRequestDto dto) {
        String token = memberService.login(dto.getEmail(), dto.getPassword());
        return new MemberLoginResponseDto(token);
    }

}