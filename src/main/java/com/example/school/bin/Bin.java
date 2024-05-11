package com.example.school.bin;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double hardness; // 경도

    @Column(nullable = false)
    private LocalDateTime data_data; // 데이터기준 날짜

    @Column(length = 200, nullable = false)
    private String location_name;

    @Column(length = 200, nullable = false)
    private String metropolitan_city;

    @Column(nullable = false)
    private double latitude;
}