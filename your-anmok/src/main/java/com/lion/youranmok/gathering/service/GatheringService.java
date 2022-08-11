package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatheringService {
    private final GatheringRepository gatheringRepository;

    GatheringService(GatheringRepository gatheringRepository) {
        this.gatheringRepository = gatheringRepository;
    }


    public List<GatheringBoard> listByExpiredFalse() {
        return gatheringRepository.listByExpiredFalse();
    }
}
