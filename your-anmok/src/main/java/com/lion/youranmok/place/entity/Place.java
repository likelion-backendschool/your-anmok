package com.lion.youranmok.place.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String name;

    private Integer star;

    private Double lat;

    private Double lon;

    @OneToMany
    List<PlaceReview> placeReviewList = new ArrayList<>();
}
