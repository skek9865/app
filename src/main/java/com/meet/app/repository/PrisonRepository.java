package com.meet.app.repository;

import com.meet.app.entity.Prison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrisonRepository extends JpaRepository<Prison, Long> {
}
