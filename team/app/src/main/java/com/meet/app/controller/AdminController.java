package com.meet.app.controller;

import com.meet.app.dto.BoardDTO;
import com.meet.app.dto.MemberDTO;
import com.meet.app.dto.PrisonDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.entity.Board;
import com.meet.app.entity.Member;
import com.meet.app.entity.RoomInfo;
import com.meet.app.repository.BoardRepository;
import com.meet.app.repository.MemberRepository;
import com.meet.app.repository.RoomInfoRepository;
import com.meet.app.service.MemberService;
import com.meet.app.service.PrisonService;
import com.meet.app.service.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    //회원 관리
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //모임 관리
    private final RoomInfoService roomInfoService;
    private final RoomInfoRepository roomInfoRepository;

    //게시판 관리
    private final BoardRepository boardRepository;

    //채팅방 관리

    //신고 관리
    private final PrisonService prisonService;

    //----------------- 회원 관리 -------------------------

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
        Member findMember = memberRepository.findById(memberDTO.getMemberID()).get();
        findMember.deleteUser();
    }

    // --------------- 모임 관리 -----------------------------
    @GetMapping("/room")
    public List<RoomInfo> getRoomList() {
        log.info("admin - roomList");
        return roomInfoRepository.findAll();
    }

//    @GetMapping("/room/board/{id}")
//    public List<BoardDTO> getBoardList(@PathVariable("id") Long id) {
//
//    }


    @DeleteMapping("/room/remove/{id}")
    public void removeRoom(@PathVariable("id") Long id) {
        log.info("admin - removeRoom" +roomInfoService.roomRead(id));
        roomInfoService.deleteRoom(id);
    }

    // --------------- 게시판 관리 ---------------------------

    @GetMapping("/board")
    public List<Board> getBoardList() {
        log.info("admin - BoardList");
        return boardRepository.findAll();
    }

    @DeleteMapping("/board/remove/{id}")
    public void removeBoard(@PathVariable("id") Long id) {
        log.info("admin - BoardRemove" + boardRepository.findById(id).get());
        boardRepository.deleteById(id);
    }

    // --------------- 채팅방 관리 ----------------------------


    // --------------- 신고 관리 -----------------------------

}
