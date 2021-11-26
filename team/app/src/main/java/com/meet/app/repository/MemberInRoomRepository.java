package com.meet.app.repository;

import com.meet.app.entity.MemberInRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberInRoomRepository extends JpaRepository<MemberInRoom,Long> {

    @Query("select mr.member.id from MemberInRoom mr where mr.roomInfo.id =:id and mr.isRoomMaster = true")
    String getMemberID(@Param("id")Long id);

    @Query("select mr from MemberInRoom mr where mr.roomInfo.id =:roomID")
    List<MemberInRoom> getMemberRoom(@Param("roomID") Long id);

    @Query(value = "update member_room set is_end = true where room_info_id =:roomID" ,nativeQuery = true)
    void deleteInRoom(@Param("roomID")Long roomID);

    @Query(value = "update member_room set is_end = true where member_id =:memberID", nativeQuery = true)
    void outRoom(@Param("memberID") String memberID);

    @Query(value = "select mr from MemberInRoom mr where mr.member.id =:memberID")
    List<MemberInRoom> getMyRoom(@Param("memberID") String memberID);
}