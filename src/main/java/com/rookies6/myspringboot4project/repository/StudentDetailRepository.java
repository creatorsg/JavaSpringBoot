package com.rookies6.myspringboot4project.repository;

import com.rookies6.myspringboot4project.entity.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDetailRepository extends JpaRepository<StudentDetail, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}