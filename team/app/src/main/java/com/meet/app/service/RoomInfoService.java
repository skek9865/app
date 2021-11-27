package com.meet.app.service;

import com.meet.app.dto.MyRoomDTO;
import com.meet.app.dto.RoomInfoDTO;
import com.meet.app.entity.Category;
import com.meet.app.entity.Member;
import com.meet.app.entity.RoomInfo;

import java.util.List;

public interface RoomInfoService {

    Long roomRegister(RoomInfoDTO roomInfoDTO);

    List<RoomInfoDTO> roomList(String id);

    RoomInfoDTO roomRead(Long roomID);

    void deleteRoom(Long roomID);

    List<MyRoomDTO> myRoomList(String memberID);

    void reStartRoom(Long roomID);

    void endRoom(Long roomID);

    List<RoomInfoDTO> hotRoomList(String memberID);

    default RoomInfoDTO entityToDTO(RoomInfo roomInfo, String memberID){

        RoomInfoDTO roomInfoDTO = RoomInfoDTO.builder()
                .id(roomInfo.getId())
                .title(roomInfo.getTitle())
                .periodic(roomInfo.isPeriodic())
                .startDate(roomInfo.getStartDate())
                .endDate(roomInfo.getEndDate())
                .nowPeople(roomInfo.getNowPeople())
                .maxPeople(roomInfo.getMaxPeople())
                .prefer(roomInfo.getPrefer())
                .place(roomInfo.getPlace())
                .views(roomInfo.getViews())
                .categoryID(roomInfo.getCategory().getId())
                .schoolID(roomInfo.getSchool().getId())
                .memberID(memberID)
                .frequency(roomInfo.getFrequency())
                .isDel(roomInfo.isDel())
                .isEnd(roomInfo.isEnd())
                .build();

        return roomInfoDTO;
    }

    default RoomInfo dtoTOEntity(RoomInfoDTO roomInfoDTO, Member member){

        Category category = Category.builder().id(roomInfoDTO.getCategoryID()).build();

        RoomInfo roomInfo = RoomInfo.builder()
                .title(roomInfoDTO.getTitle())
                .periodic(roomInfoDTO.isPeriodic())
                .startDate(roomInfoDTO.getStartDate())
                .endDate(roomInfoDTO.getEndDate())
                .maxPeople(roomInfoDTO.getMaxPeople())
                .prefer(roomInfoDTO.getPrefer())
                .place(roomInfoDTO.getPlace())
                .category(category)
                .school(member.getSchool())
                .frequency(roomInfoDTO.getFrequency())
                .build();

        return roomInfo;
    }
}
