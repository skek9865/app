package com.meet.app.repository;

import com.meet.app.entity.RoomInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomInfoRepository extends JpaRepository<RoomInfo,Long> {

    @Query(value = "update Room_info r set r.views = r.views + 1 where r.id =:roomID",nativeQuery = true)
    void plusViews(@Param("roomID") Long roomID);

    @Query(value = "update Room_info set present_people = present_people + 1 where id =:roomID",nativeQuery = true)
    void plusPerson(@Param("roomID") Long roomID);

    @Query(value = "update Room_info r set r.present_people = r.present_people - 1 where r.id =:roomID",nativeQuery = true)
    void minusPerson(@Param("roomID") Long roomID);

    @Query(value = "select r from RoomInfo r where r.school.id =:schoolNum and r.isDel = false")
    List<RoomInfo> getList(@Param("schoolNum") Integer schoolNum, Sort sort);

    @Query("select r, mr from RoomInfo r left outer join MemberInRoom mr on r.id = mr.roomInfo.id where mr.member.id =:memberID")
    List<Object[]> getMyList(@Param("memberID")String memberID, Sort sort);

    @Query(value = "update Room_info set is_del = true, is_end = true where id =:roomID",nativeQuery = true)
    void deleteRoom(@Param("roomID")Long roomID);

    @Query(value = "update Room_info set is_end = false where id =:roomID", nativeQuery = true)
    void reStartRoom(@Param("roomID")Long roomID);

    @Query(value = "update Room_info set is_end = true where id =:roomID", nativeQuery = true)
    void endRoom(@Param("roomID")Long roomID);

    @Query(value = "select * from Room_Info r where r.school_id =:schoolNum and r.maximum_people > 2 and r.is_Del = false order by r.maximum_people - r.present_people asc , r.present_people desc,  r.views desc limit 10", nativeQuery = true)
    List<RoomInfo> getHotRoom(@Param("schoolNum") Integer schoolNum);
}
