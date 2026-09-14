package com.rookies6.myspringboot4project.repository;

import com.rookies6.myspringboot4project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentNumber(String studentNumber);

    boolean existsByStudentNumber(String studentNumber);
}