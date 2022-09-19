package com.lion.youranmok.place.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Integer placeId;

    @JsonIgnore
    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private PlaceReview placeReview;

    @Column(nullable = false)
    private String origFileName;  // 파일 원본명

    @Column(nullable = false)
    private String newFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    public PlaceImage(Integer placeId, String origFileName, String newFileName, String filePath, Long fileSize){
        this.placeId = placeId;
        this.origFileName = origFileName;
        this.newFileName = newFileName;
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
