package com.rookies6.myspringboot4project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Entity
@Getter @Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    private Spring customerId;

    @Column(nullable = false)
    private String customerName;
}
