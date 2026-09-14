package com.rookies6.myspringboot4project.service;

import com.rookies6.myspringboot4project.entity.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String studentNumber;
    private StudentDetailResponse detail;

    public static StudentResponse from(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .studentNumber(student.getStudentNumber())
                .detail(StudentDetailResponse.from(student.getStudentDetail()))
                .build();
    }
}