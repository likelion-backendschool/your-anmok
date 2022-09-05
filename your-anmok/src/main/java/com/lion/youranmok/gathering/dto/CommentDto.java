package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String nickname;
    private String comment;
    private String img;
    private String createdAt;

    private String tagTo;

    private List<CommentDto> replyList;


    public CommentDto(Integer commentId, String nickname, String comment, String img, LocalDateTime createdAt, String tagTo) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.comment = comment;
        this.img = img;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        this.tagTo = tagTo;
    }

}
