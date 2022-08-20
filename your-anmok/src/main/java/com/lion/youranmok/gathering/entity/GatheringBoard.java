package com.lion.youranmok.gathering.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
public class GatheringBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Column
    private Integer placeId;

    @Column
    private Integer userId;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String text;

    @Column
    private LocalDate date;

    @Column(length = 1)
    private Boolean isExpired;

    @Column
    private Integer totalCnt;

    @Column
    private Integer gatherCnt;

}
