package com.rookies6.myspringboot4project.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudentDetailRequest {
    private String address;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    private LocalDate dateOfBirth;
}