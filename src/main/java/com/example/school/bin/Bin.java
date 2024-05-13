package com.example.school.bin;


import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data_data; // 데이터기준 날짜

    @Column(length = 200, nullable = false)
    private String location_name; // 설치 위치

    @Column(length = 200, nullable = false)
    private String metropolitan_city; // 시도명

    @Column(length = 200, nullable = false)
    private String city; // 시

    @Column(nullable = false)
    private double hardness; // 경도

    @Column(nullable = false)
    private double latitude; // 위도
}
