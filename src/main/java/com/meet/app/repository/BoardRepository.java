package com.meet.app.repository;

import com.meet.app.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b join fetch b.roomInfo where b.roomInfo.id =:id")
    List<Board> getList(@Param("id") Long roomInfoId);
}
