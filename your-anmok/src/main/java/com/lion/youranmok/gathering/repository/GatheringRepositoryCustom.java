package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.entity.GatheringListCriteria;

import java.util.List;

public interface GatheringRepositoryCustom {

    List<GatheringListDetailDto> listByCriteria(GatheringListCriteria criteria);
}
