package com.meet.app.repository;

import com.meet.app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,String> {

    @Query("select m.school.id from Member m where m.id =:id")
    Integer getSchoolName(@Param("id") String id);

}