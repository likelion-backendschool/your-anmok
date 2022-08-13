package com.lion.youranmok.gathering.dto;

import com.lion.youranmok.category.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatheringListDto {
    private List<GatheringListDetailDto> gatheringList;
    private List<CategoryDto> categoryList;

}
