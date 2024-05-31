package com.example.school.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userID;

    private String password;

    private String phoneNumber;

    private String address;

    private boolean cleaner;

    // 기본 0
    @ColumnDefault("0")
    private Long rankPoint;
}