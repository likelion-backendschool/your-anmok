package com.lion.youranmok.gathering.entity;

import com.lion.youranmok.category.dto.CategoryDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class GatheringListCriteria {
    private List<Integer> category;
    private String classification;
    private String searchKeyword;
}
