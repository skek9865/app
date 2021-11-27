package com.meet.app.service;

import com.meet.app.dto.MyRoomDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.entity.*;
import com.meet.app.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;
    private final MemberInRoomRepository memberInRoomRepository;
    private final MemberRepository memberRepository;


    // 모임 생성
    @Transactional
    @Override
    public Long roomRegister(RoomInfoDTO roomInfoDTO) {

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
    public List<RoomInfoDTO> roomList(String id) {

        log.info("memberID : " + id);

        Integer schoolNum = memberRepository.getSchoolName(id);

        List<RoomInfo> roomInfoList = roomInfoRepository.getList(schoolNum, Sort.by("id").descending());

        List<RoomInfoDTO> dtoList = new ArrayList<>();

        roomInfoList.forEach(entity -> {

            if(!entity.isEnd()){

                String memberID = memberInRoomRepository.getMemberID(entity.getId());

                RoomInfoDTO dto = entityToDTO(entity, memberID);

                dtoList.add(dto);
            }
        });

        return dtoList;
    }

    // 모임 세부 페이지
    @Override
    public RoomInfoDTO roomRead(Long roomID) {

        RoomInfo roomInfo = roomInfoRepository.findById(roomID).get();

        String memberID = memberInRoomRepository.getMemberID(roomInfo.getId());

        RoomInfoDTO roomInfoDTO = entityToDTO(roomInfo, memberID);

        roomInfoRepository.plusViews(roomID);

        return roomInfoDTO;
    }

    // 모임 삭제
    @Transactional
    @Override
    public void deleteRoom(Long roomID) {

        memberInRoomRepository.deleteInRoom(roomID);
        roomInfoRepository.deleteRoom(roomID);
    }

    // 내 모임 리스트
    @Override
    public List<MyRoomDTO> myRoomList(String memberID) {

        List<Object[]> myList = roomInfoRepository.getMyList(memberID,Sort.by("id").descending());

        List<MyRoomDTO> myRoomDTOS= new ArrayList<>();

        myList.forEach(entity -> {

            RoomInfo roomInfo = (RoomInfo)entity[0];

            MemberInRoom memberInRoom = (MemberInRoom) entity[1];

            String masterID = memberInRoomRepository.getMemberID(roomInfo.getId());

            RoomInfoDTO roomInfoDTO = entityToDTO(roomInfo, masterID);

            MyRoomDTO myRoomDTO = MyRoomDTO.builder().roomInfoDTO(roomInfoDTO).isOut(memberInRoom.isEnd()).build();

            myRoomDTOS.add(myRoomDTO);
        });

        return myRoomDTOS;
    }

    // 모임 다시 모집
    @Override
    public void reStartRoom(Long roomID) {
        roomInfoRepository.reStartRoom(roomID);
    }

    // 모임 모집 마감
    @Override
    public void endRoom(Long roomID) {

        roomInfoRepository.endRoom(roomID);
    }

    // 모집 임박 리스트 출력
    @Override
    public List<RoomInfoDTO> hotRoomList(String memberID) {

        log.info("memberID : " + memberID);

        Integer schoolNum = memberRepository.getSchoolName(memberID);

        List<RoomInfo> hotRoom = roomInfoRepository.getHotRoom(schoolNum);

        List<RoomInfoDTO> hotRoomDTOS = new ArrayList<>();

        hotRoom.forEach(entity -> {

            RoomInfoDTO roomInfoDTO = entityToDTO(entity, memberID);

            hotRoomDTOS.add(roomInfoDTO);
        });

        return hotRoomDTOS;
    }
}
