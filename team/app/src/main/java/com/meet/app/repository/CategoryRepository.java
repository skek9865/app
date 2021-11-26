package com.meet.app.repository;

import com.meet.app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "update Category c set c.inquiry_sum = c.inquiry_sum + 1 where c.id =:id",nativeQuery = true)
    void plusCount(@Param("id") Long id);
}
