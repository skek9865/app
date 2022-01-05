package com.meet.app.config.security;


import com.meet.app.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    // 시크릿 키 설정
    private String secretKey = "daramzip";

    // 토큰 유효시간 지정 (2분)
    private Long tokenValidTime = 2 * 1000 * 60L;

    // 객체 초기화, 시크릿 키 인코딩
    @PostConstruct
    protected void init(){
        String secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    // 토큰 생성 메서드
    public String createToken(String memberId, List<String> roles){

        // payload에 memberPk 저장
        Claims claims = Jwts.claims().setSubject(memberId);

        // 역할도 저장
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .setExpiration(new Date(System.currentTimeMillis()+tokenValidTime))
                .compact();
    }

    // 토큰에서 memberId 얻기
    public String getMemberPk(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Request의 Header에서 token 값 가져오기
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("JWT-TOKEN");
    }

    public Boolean validateToken(String token) {
        try{
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

}
