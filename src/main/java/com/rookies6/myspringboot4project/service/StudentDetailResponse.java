package com.rookies6.myspringboot4project.service;

import com.rookies6.myspringboot4project.entity.StudentDetail;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class StudentDetailResponse {
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;

    public static StudentDetailResponse from(StudentDetail detail) {
        return StudentDetailResponse.builder()
                .address(detail.getAddress())
                .phoneNumber(detail.getPhoneNumber())
                .email(detail.getEmail())
                .dateOfBirth(detail.getDateOfBirth())
                .build();
    }
}