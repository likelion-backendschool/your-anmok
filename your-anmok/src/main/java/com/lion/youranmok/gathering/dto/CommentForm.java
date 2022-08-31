package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class CommentForm {
    private String content;
    private String mentionTo;
    private Integer applyTo;
}
