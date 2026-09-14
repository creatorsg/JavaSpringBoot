package com.rookies6.myspringboot4project.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("존재하지 않는 학생입니다. id=" + id);
    }
}