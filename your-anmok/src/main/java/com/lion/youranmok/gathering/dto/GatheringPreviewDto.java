package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatheringPreviewDto {

    private Integer boardId;

    private String title;

    private LocalDate gatheringDate;

    private String placeName;

    private Integer commentCnt;

    public GatheringPreviewDto(Integer boardId, String title, LocalDate gatheringDate, String placeName) {
        this.boardId = boardId;
        this.title = title;
        this.gatheringDate = gatheringDate;
        this.placeName = placeName;
    }

}
