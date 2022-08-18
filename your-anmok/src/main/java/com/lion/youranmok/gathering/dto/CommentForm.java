package com.lion.youranmok.gathering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommentForm {
    private String content;
    private Integer mentionTo;
    private Integer applyTo;
}
