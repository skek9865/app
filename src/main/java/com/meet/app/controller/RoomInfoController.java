package com.meet.app.controller;

import com.meet.app.dto.MemberDTO;
import com.meet.app.dto.MyRoomDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.service.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomInfoController {

    private final RoomInfoService roomInfoService;

    // 모임 생성
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RoomInfoDTO roomInfoDTO){
        log.info("RoomInfoController - register");
        log.info("roomInfoDTO : " + roomInfoDTO);

        Long result = roomInfoService.register(roomInfoDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 모임 리스트 출력
    @PostMapping(value = "/getList")
    public ResponseEntity<List<RoomInfoDTO>> getList(@RequestBody MemberDTO memberDTO){
        log.info("RoomInfoController - getList");
        log.info("memberID : " + memberDTO.getMemberID());

        List<RoomInfoDTO> dtoList = roomInfoService.getList(memberDTO.getMemberID());

        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    // 모임 세부 페이지
    @PostMapping("/getOne/{roomID}")
    public ResponseEntity<RoomInfoDTO> getOne(@PathVariable("roomID") Long roomID){
        log.info("RoomInfoController - getOne");
        log.info("roomID : " + roomID);

        RoomInfoDTO room_infoDTO = roomInfoService.getOne(roomID);

        return new ResponseEntity<>(room_infoDTO,HttpStatus.OK);
    }

    // 모임 삭제
    @CrossOrigin
    @PutMapping("/remove/{roomID}")
    public HttpStatus removeRoom(@PathVariable("roomID") Long roomID){
        log.info("RoomInfoController - deleteRoom");
        log.info("roomID : " + roomID);

        roomInfoService.remove(roomID);

        return HttpStatus.OK;
    }


    // 내 모임 리스트
    @PostMapping("/myRoomList")
    public ResponseEntity<List<MyRoomDTO>> myRoomList(@RequestBody MemberDTO memberDTO){
        log.info("RoomInfoController - myRoomList");
        log.info("MemberID : " + memberDTO.getMemberID());

        List<MyRoomDTO> myRoomDTOS = roomInfoService.myRoomList(memberDTO.getMemberID());

        return new ResponseEntity<>(myRoomDTOS, HttpStatus.OK);
    }

    // 모임 다시 모집
    @PutMapping("/reStartRoom/{roomID}")
    public HttpStatus reStartRoom(@PathVariable("roomID") Long roomID){
        log.info("RoomInfoController - reStartRoom");
        log.info("roomID : " + roomID);

        roomInfoService.reStartRoom(roomID);

        return HttpStatus.OK;
    }

    // 모임 모집 마감
    @PutMapping("/endRoom/{roomID}")
    public HttpStatus endRoom(@PathVariable("roomID") Long roomID){
        log.info("RoomInfoController - endRoom");
        log.info("roomID : " + roomID);

        roomInfoService.endRoom(roomID);

        return HttpStatus.OK;
    }

    // 모집 임박 리스트
    @PostMapping("/hotRoomList")
    public ResponseEntity<List<RoomInfoDTO>> hotRoomList(@RequestBody MemberDTO memberDTO){
        log.info("RoomInfoController - hotRoomList");
        log.info("memberDTO : " + memberDTO);

        List<RoomInfoDTO> roomInfoDTOS = roomInfoService.hotRoomList(memberDTO.getMemberID());

        return new ResponseEntity<>(roomInfoDTOS, HttpStatus.OK);
    }
}
