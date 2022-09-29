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

    private Integer totalPlaceCnt;

    private boolean existImage;

    private String imagePath;


    public CategorySortingDto(Integer id, String tagName, long bookmarkCnt, long totalPlaceCnt, String imagePath) {
        this.id = id;
        this.tagName = tagName;
        this.bookmarkCnt = (int) bookmarkCnt;
        this.totalPlaceCnt = (int) totalPlaceCnt;
        this.existImage = false;

//        if(imagePath == null) {
//            this.imagePath = "/image/category/%s.jpg".formatted(((int) (Math.random() * 6) + 1));
//        } else {
            this.imagePath = "/image/category/%s".formatted(imagePath);
//        }
    }

}
