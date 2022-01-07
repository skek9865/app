package com.meet.app.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 토큰을 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰이 유효한지 확인
        if(token != null && jwtTokenProvider.validateToken(token)){
            // 토큰이 유효하면 유저정보를 받아옴
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            // SecurityContext에 Authentication객체를 저장한다
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }
}
