package com.lion.youranmok.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNameDto {
    // 다정 : 번개모임에서 해시태그 리스트 받아올때 사
    private Integer id;
    private String categoryName;
}
