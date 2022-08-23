package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;

    public List<GatheringListDetailDto> listByCriteria() {
        return gatheringRepository.listByCriteria();
    }

    public GatheringDetailDto getDetailById(int id) {
        return gatheringRepository.getDetailById(id);
    }


    public GatheringBoard findById(int id) {
        return gatheringRepository.findById(id).orElse(null);
    }
}
