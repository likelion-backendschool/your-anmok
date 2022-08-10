package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer recruitCnt;
    private Integer curCnt;

    public GatheringListDetailDto(Integer boardId, String store, String title, LocalDateTime meetAt, Integer recruitCnt, Integer curCnt) {
        this.boardId = boardId;
        this.store = store;
        this.title = title;
        this.meetAt = meetAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
        this.recruitCnt = recruitCnt;
        this.curCnt = curCnt;
    }

    public void setMeetAt(LocalDateTime meetAt) {
        this.meetAt = meetAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
    }
}
