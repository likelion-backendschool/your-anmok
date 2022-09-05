package com.lion.youranmok.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySortingDto {

    private Integer id;
    private String tagName;

    private boolean isBookmark;

    private Integer bookmarkCnt;


    public CategorySortingDto(Integer id, String tagName, long bookmarkCnt) {
        this.id = id;
        this.tagName = tagName;
        this.bookmarkCnt = (int) bookmarkCnt;
    }

}
