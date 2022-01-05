package com.meet.app.repository;

import com.meet.app.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

    @Query("select m.school.id from Member m where m.id =:id")
    Optional<Integer> getSchoolNum(@Param("id") String id);

    @EntityGraph(attributePaths = {"roles"})
    Optional<Member> findById(String id);

}