package com.lion.youranmok.gathering.converter;

import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;

public class GatheringListConverter {
    public GatheringListDto convert(GatheringBoard gatheringBoard) {
        GatheringListDetailDto gatheringListDetailDto = new GatheringListDetailDto();
        gatheringListDetailDto.setBoardId(gatheringBoard.getId());
        gatheringListDetailDto.setStore(gatheringBoard.setStore);
        gatheringListDetailDto.setTotalCnt(gatheringBoard.getTotalCnt());


        return gatheringListDto;
    }


}
