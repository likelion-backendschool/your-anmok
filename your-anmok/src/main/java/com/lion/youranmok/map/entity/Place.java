package com.lion.youranmok.map.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String name;

    @Column
    private Integer rateCnt;

    @Column
    private Integer star;

    @Column
    private double lat;

    @Column
    private double lon;
}
