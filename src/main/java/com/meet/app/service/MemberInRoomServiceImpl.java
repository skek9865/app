package com.meet.app.service;

import com.meet.app.entity.Member;
import com.meet.app.entity.MemberInRoom;
import com.meet.app.entity.RoomInfo;
import com.meet.app.repository.MemberInRoomRepository;
import com.meet.app.repository.RoomInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberInRoomServiceImpl implements MemberInRoomService{

    private final MemberInRoomRepository memberInRoomRepository;
    private final RoomInfoRepository roomInfoRepository;

    // 모임 참가
    @Transactional
    @Override
    public Boolean inRoom(Long roomID, String memberID) {
        log.info("MemberInRoomService - inRoom");
        log.info("roomID : " + roomID);
        log.info("memberID : " + memberID);

        List<MemberInRoom> memberRooms = memberInRoomRepository.getMemberRoom(roomID);

        for(MemberInRoom memberInRoom : memberRooms){

            if(memberInRoom.getMember().getId().equals(memberID)){

                return false;
            }
        }

        roomInfoRepository.plusPerson(roomID);

        RoomInfo roomInfo = RoomInfo.builder().id(roomID).build();

        Member member = Member.builder().id(memberID).build();

        MemberInRoom memberInRoom= MemberInRoom.builder()
                .member(member).roomInfo(roomInfo).isRoomMaster(false).build();

        memberInRoomRepository.save(memberInRoom);

        return true;
    }

    // 모임 탈퇴
    @Transactional
    @Override
    public Boolean outRoom(Long roomID, String memberID) {
        log.info("MemberInRoomService - outRoom");
        log.info("roomID : " + roomID);
        log.info("memberID : " + memberID);

        List<MemberInRoom> memberRooms = memberInRoomRepository.getMemberRoom(roomID);

        for(MemberInRoom memberInRoom : memberRooms){

            if(memberInRoom.getMember().getId().equals(memberID) && !memberInRoom.isEnd()){

                memberInRoomRepository.outRoom(memberID);

                roomInfoRepository.minusPerson(roomID);

                return Boolean.TRUE;
            }
        }

       return Boolean.FALSE;
    }
}
