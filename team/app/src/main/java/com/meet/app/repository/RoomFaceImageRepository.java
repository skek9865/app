package com.meet.app.repository;

import com.meet.app.entity.RoomFaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoomFaceImageRepository extends JpaRepository<RoomFaceImage, Long> {

    @Query("select ri from RoomFaceImage ri where ri.roomInfo.id =:roomID")
    Optional<RoomFaceImage> getImage(@Param("roomID")Long roomID);
}
