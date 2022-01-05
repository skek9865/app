package com.meet.app.controller;

import com.meet.app.dto.MemberDTO;
import com.meet.app.entity.Member;
import com.meet.app.repository.BoardRepository;
import com.meet.app.repository.MemberRepository;
import com.meet.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Log4j2
public class AdminController {
    //회원 관리
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    //게시판 관리
    private final BoardRepository boardRepository;

    @GetMapping("/user")
    public List<MemberDTO> getUserList() {
        log.info("admin - UserList");
        return memberService.getList();
    }

    @GetMapping("/user/{id}")
    public MemberDTO getUser(@PathVariable("id") String id) {
        log.info("admin - UserOne");
        return memberService.getMember(id);
    }

    @PutMapping("/user/")

    @DeleteMapping("/user/remove")
    public void removeMember(@RequestBody MemberDTO memberDTO) {
        String memberID = memberDTO.getMemberID();
        Member findMember = memberRepository.findById(memberID).get();
        memberRepository.delete(findMember);
        findMember.deleteUser();
    }
}
