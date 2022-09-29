package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatheringListDetailDto {

    private Integer boardId;
    private String store;
    private String title;
    private String meetAt;
    private Integer totalCnt;
    private Integer gatherCnt;
    private LocalDateTime createdAt;

    private String filePath;

    private String categoryImg;


    public GatheringListDetailDto(Integer boardId, String store, String title, LocalDate meetAt, Integer totalCnt, Integer gatherCnt, LocalDateTime createdAt, String filePath, String categoryImg) {
        this.boardId = boardId;
        this.store = store;
        this.title = title;
        this.meetAt = meetAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        this.totalCnt = totalCnt;
        this.gatherCnt = gatherCnt;
        this.createdAt = createdAt;
        this.filePath = filePath;
        this.categoryImg = categoryImg;
    }

    public void setMeetAt(LocalDate meetAt) {
        this.meetAt = meetAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }
}
