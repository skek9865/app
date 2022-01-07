package com.meet.app.service;

import com.meet.app.dto.MyRoomDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.entity.*;
import com.meet.app.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;
    private final MemberInRoomRepository memberInRoomRepository;
    private final MemberRepository memberRepository;

    // 모임 생성
    @Transactional
    @Override
    public Long register(RoomInfoDTO roomInfoDTO) {
        log.info("RoomInfoService - register");
        log.info("roomInfoDTO : " + roomInfoDTO);

        Optional<Member> memberResult = memberRepository.findById(roomInfoDTO.getMemberID());

        Member member = memberResult.get();

        RoomInfo roomInfo = dtoTOEntity(roomInfoDTO, member);

        MemberInRoom memberInRoom = MemberInRoom.builder()
                        .member(member).roomInfo(roomInfo).isRoomMaster(true).build();

        roomInfoRepository.save(roomInfo);
        memberInRoomRepository.save(memberInRoom);

        return roomInfo.getId();
    }

    // 모임 리스트 출력
    @Override
    public List<RoomInfoDTO> getList(String memberID) {
        log.info("RoomInfoService - getList");
        log.info("memberID : " + memberID);

        Optional<Integer> result = memberRepository.getSchoolNum(memberID);

        Integer schoolNum = result.get();

        List<RoomInfo> roomInfoList = roomInfoRepository.getList(schoolNum, Sort.by("id").descending());

        List<RoomInfoDTO> dtoList = new ArrayList<>();

        roomInfoList.forEach(entity -> {

            // 삭제 되지 않은 방 검색
            if(!entity.isEnd()){

                Optional<String> getMasterID = memberInRoomRepository.getMemberID(entity.getId());

                String masterID = getMasterID.get();

                RoomInfoDTO dto = entityToDTO(entity, masterID);

                dtoList.add(dto);
            }
        });

        return dtoList;
    }

    // 모임 세부 페이지
    @Override
    public RoomInfoDTO getOne(Long roomID) {
        log.info("RoomInfoService - getOne");
        log.info("roomID : " + roomID);

        Optional<RoomInfo> getRoom = roomInfoRepository.findById(roomID);

        RoomInfo roomInfo = getRoom.get();

        Optional<String> getMasterID = memberInRoomRepository.getMemberID(roomInfo.getId());

        String masterID = getMasterID.get();

        RoomInfoDTO roomInfoDTO = entityToDTO(roomInfo, masterID);

        roomInfoRepository.plusViews(roomID);

        return roomInfoDTO;
    }

    // 모임 삭제
    @Transactional
    @Override
    public void remove(Long roomID) {
        log.info("RoomInfoService - remove");
        log.info("roomID : " + roomID);

        memberInRoomRepository.deleteInRoom(roomID);
        roomInfoRepository.deleteRoom(roomID);
    }

    // 내 모임 리스트
    @Override
    public List<MyRoomDTO> myRoomList(String memberID) {
        log.info("RoomInfoService - myRoomList");
        log.info("memberID : " + memberID);

        List<Object[]> myList = roomInfoRepository.getMyList(memberID,Sort.by("id").descending());

        List<MyRoomDTO> myRoomDTOS= new ArrayList<>();

        myList.forEach(entity -> {

            RoomInfo roomInfo = (RoomInfo)entity[0];

            MemberInRoom memberInRoom = (MemberInRoom) entity[1];

            Optional<String> getMasterID = memberInRoomRepository.getMemberID(roomInfo.getId());

            String masterID = getMasterID.get();

            RoomInfoDTO roomInfoDTO = entityToDTO(roomInfo, masterID);

            MyRoomDTO myRoomDTO = MyRoomDTO.builder().roomInfoDTO(roomInfoDTO).isOut(memberInRoom.isEnd()).build();

            myRoomDTOS.add(myRoomDTO);
        });

        return myRoomDTOS;
    }

    // 모임 다시 모집
    @Override
    public void reStartRoom(Long roomID) {
        log.info("RoomInfoService - reStartRoom");
        log.info("roomID : " + roomID);

        roomInfoRepository.reStartRoom(roomID);
    }

    // 모임 모집 마감
    @Override
    public void endRoom(Long roomID) {
        log.info("RoomInfoService - endRoom");
        log.info("roomID : " + roomID);

        roomInfoRepository.endRoom(roomID);
    }

    // 모집 임박 리스트 출력
    @Override
    public List<RoomInfoDTO> hotRoomList(String memberID) {
        log.info("RoomInfoService - hotRoomList");
        log.info("memberID : " + memberID);

        Optional<Integer> getSchoolNum = memberRepository.getSchoolNum(memberID);

        Integer schoolNum = getSchoolNum.get();

        List<RoomInfo> hotRoom = roomInfoRepository.getHotRoom(schoolNum);

        List<RoomInfoDTO> hotRoomDTOS = new ArrayList<>();

        hotRoom.forEach(entity -> {

            RoomInfoDTO roomInfoDTO = entityToDTO(entity, memberID);

            hotRoomDTOS.add(roomInfoDTO);
        });

        return hotRoomDTOS;
    }
}
