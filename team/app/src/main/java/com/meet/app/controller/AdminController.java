package com.meet.app.controller;

import com.meet.app.dto.MemberDTO;
import com.meet.app.dto.PrisonDTO;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //신고 관리
    private final PrisonService prisonService;

    //----------------- 회원 관리 -------------------------

    @GetMapping("/user")
    public List<MemberDTO> getUserList() {
        log.info("AdminController - UserList");
        return memberService.getList();
    }

    @GetMapping("/user/{id}")
    public MemberDTO getUser(@PathVariable("id") String id) {
        log.info("AdminController - UserOne");
        log.info("id : " + id);
        return memberService.getOne(id);
    }

    @PutMapping("/user/remove")
    public void removeMember(@RequestBody MemberDTO memberDTO) {
        log.info("AdminController - UserRemove");
        log.info("memberDTO : " + memberDTO);
        Member findMember = memberRepository.findById(memberDTO.getMemberID()).get();
        findMember.deleteUser();
    }

    // --------------- 모임 관리 -----------------------------
    @GetMapping("/room")
    public List<RoomInfo> getRoomList() {
        log.info("AdminController - roomList");
        return roomInfoRepository.findAll();
    }

    @PutMapping("/room/remove/{id}")
    public void removeRoom(@PathVariable("id") Long id) {
        log.info("AdminController - removeRoom" +roomInfoService.getOne(id));
        log.info("id : " + id);
        roomInfoService.remove(id);
    }

    // --------------- 게시판 관리 ---------------------------

    @GetMapping("/board")
    public List<Board> getBoardList() {
        log.info("AdminController - BoardList");
        return boardRepository.findAll();
    }

    @DeleteMapping("/board/remove/{id}")
    public void removeBoard(@PathVariable("id") Long id) {
        log.info("AdminController - BoardRemove" + boardRepository.findById(id).get());
        log.info("id : " + id);
        boardRepository.deleteById(id);
    }

    // --------------- 채팅방 관리 ----------------------------


    // --------------- 신고 관리 -----------------------------

    @GetMapping("/prison")
    public ResponseEntity<List<PrisonDTO>> getPrisonList() {
        log.info("AdminController - PrisonList");
        List<PrisonDTO> prisonDtoList = prisonService.getList();
        return new ResponseEntity<>(prisonDtoList, HttpStatus.OK);
    }

    @GetMapping("/prison/{id}")
    public ResponseEntity<PrisonDTO> getPrison(@PathVariable("id") Long id){
        log.info("AdminController - PrisonOne");
        log.info("id : " + id);
        PrisonDTO prisonDTO = prisonService.getOne(id);
        return new ResponseEntity<>(prisonDTO, HttpStatus.OK);
    }

    @DeleteMapping("/prison/{id}")
    public HttpStatus removePrison(@PathVariable("id") Long id){
        log.info("AdminController - PrisonDelete");
        log.info("id : " + id);
        prisonService.remove(id);
        return HttpStatus.OK;
    }
}
