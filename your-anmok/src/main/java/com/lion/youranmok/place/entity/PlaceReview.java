package com.lion.youranmok.place.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

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
    private Integer placeId;
    private Integer star;
    private String placeImg;
}