package com.rookies6.myspringboot4project.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StudentCreateRequest {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentNumber;

    @NotNull(message = "상세정보는 필수입니다.")
    @Valid
    private StudentDetailRequest detailRequest;
}