package com.example.overflow.config.filter;

import com.example.overflow.config.util.JwtTokenUtils;
import com.example.overflow.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final MemberService memberService;

    @Value("${jwt.secret-key}")
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // 1. 헤더에서 토큰 찾기
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String token;
        try {
            if (header == null || !header.startsWith("Bearer ")) {
                log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
                chain.doFilter(request, response);
                return;
            }
            token = header.split(" ")[1].trim();

            // 2. userName이 DB에 있는지 확인
            String userName = JwtTokenUtils.getUsername(token, secretKey);
            JwtUserDetails userDetails = (JwtUserDetails) memberService.loadUserByUsername(userName);


            // 3. Jwt
            if (!JwtTokenUtils.validate(token, userDetails.getUsername(), secretKey)) {
                chain.doFilter(request, response);
                return;
            }

            // 4. 권한 부여
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userName,
                    null,
                    userDetails.getAuthorities()
            );
            // 5. HTTP 요청에 대한 세부 정보 넣어주기
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 6. 인증된 사용자의 정보 SecurityContex에 저장하기
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (RuntimeException e) {
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

}