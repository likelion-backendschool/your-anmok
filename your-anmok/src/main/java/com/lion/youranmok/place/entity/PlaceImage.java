package com.lion.youranmok.place.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@Data
@SuperBuilder
@NoArgsConstructor
public class PlaceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private PlaceReview placeReview;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    public PlaceImage(String origFileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // PlaceReview 정보 저장
    public void setBoard(PlaceReview placeReview){
        this.placeReview = placeReview;

        // 게시글에 현재 파일이 존재하지 않는다면
        if(!placeReview.getPlaceImageList().contains(this))
            // 파일 추가
            placeReview.getPlaceImageList().add(this);
    }
}
