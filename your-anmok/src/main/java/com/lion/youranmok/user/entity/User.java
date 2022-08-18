package com.lion.youranmok.user.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(length = 30)
    private String nickname;

    @Column(length = 50)
    private String profilePicture;

    @Column(length = 100)
    private String token;

}
