package com.example.overflow.service;

import com.example.overflow.advice.BusinessLogicException;
import com.example.overflow.advice.ExceptionCode;
import com.example.overflow.entity.Member;
import com.example.overflow.repository.MemberRepository;
import com.example.overflow.config.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    @Transactional
    public Member join(String email, String password, String userName, String phone) {
        // 이미 회원가입이 된 경우
        memberRepository.findByEmail(email).ifPresent(it -> {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        });
        // 회원가입 진행
        Member member = Member.builder()
                    .email(email)
                    .password(encoder.encode(password))
                    .userName(userName)
                    .phone(phone)
                    .build();

        return memberRepository.save(member);
    }


    @Transactional
    public String login(String email, String password) {
        // 회원가입 여부 확인
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        // 비밀번호 체크 - 암호화 되어있으니깐 이것도 고려
        if (!encoder.matches(password, member.getPassword()))
            throw new BusinessLogicException(ExceptionCode.INVALID_PASSWORD);

        // 로그인 진행
        return JwtTokenUtils.generateAccessToken(member.getUserName(),secretKey, expiredTimeMs);
    }


    public Member findUserByUserName(String userName) throws UsernameNotFoundException {
        return memberRepository.findByUserName(userName).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberById(Integer memberId) { //질문삭제전에 작성자 확인 위에꺼 써도 될지 몰라서 만들었어요
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User Not found"));

        return new org.springframework.security.core.userdetails.User(member.getUserName(), member.getPassword(), new ArrayList<>());
    }


}