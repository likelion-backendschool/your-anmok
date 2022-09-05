package com.lion.youranmok.category.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class BookmarkDto {

    private int id;

    private int userId;

    private int categoryId;

}
