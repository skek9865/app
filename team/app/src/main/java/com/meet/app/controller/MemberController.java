package com.meet.app.controller;

import com.meet.app.dto.MemberDTO;
import com.meet.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public HttpStatus register(@RequestBody MemberDTO memberDTO){

        log.info("memberDTO : " + memberDTO);

        memberService.register(memberDTO);

        return HttpStatus.OK;
    }
}
