package com.lion.youranmok.place.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceGatheringDto {
    private Integer id;
    private Integer placeId;
    private String title;
}
