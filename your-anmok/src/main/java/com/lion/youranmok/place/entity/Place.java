package com.lion.youranmok.place.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Place {
    @Id
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
