package com.lion.youranmok.place.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    //private Integer placeId;
    private Integer star;
    private String placeImg;
    @ManyToOne
    private Place place;
    @OneToMany
    List<PlaceImage> placeImageList = new ArrayList<>();
}