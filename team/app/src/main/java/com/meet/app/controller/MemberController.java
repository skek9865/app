package com.meet.app.controller;

import com.meet.app.config.security.JwtTokenProvider;
import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody MemberDTO memberDTO){
        log.info("MemberController - register");
        log.info("memberDTO : " + memberDTO);

        memberService.register(memberDTO);

        return HttpStatus.OK;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> member) {
        log.info("MemberController - login");
        log.info("member : " + member);

        Member login = memberService.login(member);

        return jwtTokenProvider.createToken(login.getId(), login.getRoles());
    }
}
