package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String nickname;
    private String comment;
    private String img;
    private String createdAt;

    public CommentDto(Integer commentId, String nickname, String comment, String img, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.comment = comment;
        this.img = img;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 오후 H:mm"));
    }

}
