package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentMyPageDto {
    private String comment;
    private String createdAt;

    private int boardId;

    private List<CommentMyPageDto> replyList;


    public CommentMyPageDto(String comment, LocalDateTime createdAt, int boardId) {

        this.comment = comment;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.boardId = boardId;
    }

}
