package com.lion.youranmok.place.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@Data
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String name;

    private Integer rateCnt;

    private Integer star;

    private Double lat;

    private Double lon;
}
