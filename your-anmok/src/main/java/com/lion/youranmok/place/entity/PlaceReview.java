package com.lion.youranmok.place.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    //private Integer placeId;
    private Integer star;
    //private String placeImg;
    @ManyToOne
    private Place place;
    @OneToMany(
            mappedBy = "placeReview",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    List<PlaceImage> placeImageList = new ArrayList<>();

    @Builder
    public PlaceReview(Integer star, Place place){
        this.star = star;
        this.place = place;
    }

    // Board에서 파일 처리 위함
    public void addPhoto(PlaceImage placeImageList) {
        this.placeImageList.add(placeImageList);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(placeImageList.getPlaceReview() != this)
            // 파일 저장
            placeImageList.setBoard(this);
    }
}