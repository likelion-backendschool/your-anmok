package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatheringDetailDto {
    //댓글 정보
    private Integer commentCnt;
    private List<CommentDto> commentList;

    //게시글 정보
    private String title;
    private String nickname;
    private String createdAt;
    private Integer totalCnt;
    private Integer gatherCnt;
    private String gatheringDate;

    //장소 정보
    private String placeId;
    private String placeName;
    private String address;
    private Integer star;
}
