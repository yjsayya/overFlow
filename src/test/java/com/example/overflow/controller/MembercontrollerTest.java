package com.example.overflow.controller;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.dto.request.MemberJoinRequestDto;
import com.example.overflow.dto.request.MemberLoginRequestDto;
import com.example.overflow.entity.Member;
import com.example.overflow.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MembercontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithAnonymousUser
    public void 회원가입() throws Exception {
        String email = "email";
        String password = "password";
        String userName = "username";
        String phone = "phone";
        // mocking
        when(memberService.join(email,password,userName,phone)).thenReturn(mock(Member.class));

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto("email1","password1","userName1","phone1"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void 회원가입_이미회원가입된경우() throws Exception {
        String email = "email";
        String password = "password";
        String userName = "username";
        String phone = "phone";

        when(memberService.join(email,password,userName,phone)).thenThrow(new BusinessLogicException(ExceptionCode.MEMBER_EXISTS));

        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberJoinRequestDto("name", "password","username","phone"))))
                .andDo(print())
                .andExpect(status().is(ExceptionCode.MEMBER_EXISTS.getStatus()));
    }


    @Test
    @WithAnonymousUser
    public void 로그인() throws Exception {
        String email = "email";
        String password = "password";

        when(memberService.login(email, password)).thenReturn("testToken");

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto("email", "password"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void 로그인_회원가입되어있지않은경우() throws Exception {
        String email = "email";
        String password = "password";

        when(memberService.login(email, password)).thenThrow(new BusinessLogicException(ExceptionCode.MEMBER_EXISTS));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto("email", "password"))))
                .andDo(print())
                .andExpect(status().is(ExceptionCode.MEMBER_NOT_FOUND.getStatus()));
    }

    @Test
    @WithAnonymousUser
    public void 로그인_비밀번호불일치() throws Exception {
        String email = "email";
        String password = "password";

        // mocking
        when(memberService.login(email, password)).thenThrow(new BusinessLogicException(ExceptionCode.INVALID_PASSWORD));

        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new MemberLoginRequestDto("email", "password"))))
                .andDo(print())
                .andExpect(status().is(ExceptionCode.INVALID_PASSWORD.getStatus()));
    }


}