package com.example.overflow.config;

import com.example.overflow.config.filter.JwtTokenFilter;
import com.example.overflow.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    // @Override
    protected void configure1(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests() // request를 인가하겠다
                // join과 login은 모두 허용
                .antMatchers("/member/login", "/member/join").permitAll()
                // join과 login을 제외한 모든 post요청, delete요청, put요청은 인가 과정 없이 접근 불가
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
                .antMatchers(HttpMethod.PUT).authenticated().and()
                // session에 대한 설정
                .sessionManagement()
                // jwt를 이용하는 경우 세션 안 쓸거니깐 설정에서 제거
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // jwt를 열어서 확인하는 과정
                .addFilterBefore(new JwtTokenFilter(memberService,secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}