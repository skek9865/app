package com.meet.app.controller;

import com.meet.app.dto.MemberDTO;
import com.meet.app.dto.MyRoomDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.service.RoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomInfoController {

    private final RoomInfoService roomInfoService;

    // 모임 생성
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RoomInfoDTO roomInfoDTO){

        log.info("roomInfoDTO : " + roomInfoDTO);

        Long result = roomInfoService.roomRegister(roomInfoDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 모임 리스트 출력
    @PostMapping(value = "/getList")
    public ResponseEntity<List<RoomInfoDTO>> getList(@RequestBody MemberDTO memberDTO){

        log.info("memberID : " + memberDTO.getMemberID());

        List<RoomInfoDTO> dtoList = roomInfoService.roomList(memberDTO.getMemberID());

        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    // 모임 세부 페이지
    @PostMapping("/getOne/{roomID}")
    public ResponseEntity<RoomInfoDTO> getOne(@PathVariable("roomID") Long roomID){

        log.info("roomID : " + roomID);

        RoomInfoDTO room_infoDTO = roomInfoService.roomRead(roomID);

        return new ResponseEntity<>(room_infoDTO,HttpStatus.OK);
    }

    // 모임 삭제
    @PutMapping("/delete/{roomID}")
    public HttpStatus deleteRoom(@PathVariable("roomID") Long roomID){

        log.info("roomID : " + roomID);

        roomInfoService.deleteRoom(roomID);

        return HttpStatus.OK;
    }


    // 내 모임 리스트
    @PostMapping("/myRoomList")
    public ResponseEntity<List<MyRoomDTO>> myNowRoomList(@RequestBody MemberDTO memberDTO){

        log.info("MemberID : " + memberDTO.getMemberID());

        List<MyRoomDTO> myRoomDTOS = roomInfoService.myRoomList(memberDTO.getMemberID());

        return new ResponseEntity<>(myRoomDTOS, HttpStatus.OK);
    }

    // 모임 다시 모집
    @PutMapping("/reStartRoom/{roomID}")
    public HttpStatus reStartRoom(@PathVariable("roomID") Long roomID){

        log.info("roomID : " + roomID);

        roomInfoService.reStartRoom(roomID);

        return HttpStatus.OK;
    }

    // 모임 모집 마감
    @PutMapping("/endRoom/{roomID}")
    public HttpStatus endRoom(@PathVariable("roomID") Long roomID){

        log.info("roomID : " + roomID);

        roomInfoService.endRoom(roomID);

        return HttpStatus.OK;
    }

    // 모집 임박 리스트
    @PostMapping("/hotRoomList")
    public ResponseEntity<List<RoomInfoDTO>> hotRoomList(@RequestBody MemberDTO memberDTO){

        log.info("memberDTO : " + memberDTO);

        List<RoomInfoDTO> roomInfoDTOS = roomInfoService.hotRoomList(memberDTO.getMemberID());

        return new ResponseEntity<>(roomInfoDTOS, HttpStatus.OK);
    }
}
