package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GatheringDetailDto {

    private String title;
//    private String nickname;
    private LocalDateTime createdAt;
    private String text;

    private Integer totalCnt;
    private Integer gatherCnt;
    private LocalDate gatheringDate;

    private Integer placeId;
    private String placeName;
    private String address;
    private Integer star;
}
