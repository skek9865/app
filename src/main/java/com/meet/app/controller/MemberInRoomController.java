package com.meet.app.controller;

import com.meet.app.dto.MemberInRoomDTO;
import com.meet.app.service.MemberInRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/memberInRoom")
public class MemberInRoomController {

    private final MemberInRoomService memberInRoomService;

    // 모임 참가
    @PutMapping("/inRoom")
    public ResponseEntity<Boolean> inRoom(@RequestBody MemberInRoomDTO memberInRoomDTO){

        Boolean result = memberInRoomService.inRoom(memberInRoomDTO.getRoomID(), memberInRoomDTO.getMemberID());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 모임 탈퇴
    @PutMapping("/outRoom")
    public ResponseEntity<Boolean> outRoom(@RequestBody MemberInRoomDTO memberInRoomDTO){

        Boolean result = memberInRoomService.outRoom(memberInRoomDTO.getRoomID(), memberInRoomDTO.getMemberID());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
