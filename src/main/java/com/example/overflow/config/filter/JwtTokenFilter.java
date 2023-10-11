//package com.example.overflow.config.filter;
//
//import com.example.overflow.entity.Member;
//import com.example.overflow.service.MemberService;
//import com.example.overflow.util.JwtTokenUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
////extends OncePerRequestFilter
//public class JwtTokenFilter  {
//
//    private final MemberService memberService;
//
//    private final String secretKey;
//
//    private final static List<String> TOKEN_IN_PARAM_URLS = List.of("/api/v1/users/alarm/subscribe");
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request,
////                                    HttpServletResponse response,
////                                    FilterChain chain)
////            throws ServletException, IOException {
////        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
////        final String token;
////        try {
////            if (TOKEN_IN_PARAM_URLS.contains(request.getRequestURI())) {
////                log.info("Request with {} check the query param", request.getRequestURI());
////                token = request.getQueryString().split("=")[1].trim();
////            } else if (header == null || !header.startsWith("Bearer ")) {
////                log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
////                chain.doFilter(request, response);
////                return;
////            } else {
////                token = header.split(" ")[1].trim();
////            }
////
////            String userName = JwtTokenUtils.getUsername(token, secretKey);
////            Member memberDetails = memberService.findUserByUserName(userName);
////
////            if (!JwtTokenUtils.validate(token, memberDetails.getUserName(), secretKey)) {
////                chain.doFilter(request, response);
////                return;
////            }
////            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
////                    memberDetails, null
////            );
////            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////        } catch (RuntimeException e) {
////            chain.doFilter(request, response);
////            return;
////        }
////
////        chain.doFilter(request, response);
////
////    }
//
//}