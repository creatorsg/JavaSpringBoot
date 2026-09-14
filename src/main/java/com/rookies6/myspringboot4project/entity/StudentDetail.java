package com.rookies6.myspringboot4project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//StudentDetail 클래스
@Entity
@Table(name = "student_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class StudentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_detail_id")
    private Long id;

    @Column
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;
}