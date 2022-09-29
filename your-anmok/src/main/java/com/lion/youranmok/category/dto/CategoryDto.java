package com.lion.youranmok.category.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer id;
    private String tagName;
    private String imgPath;

}
