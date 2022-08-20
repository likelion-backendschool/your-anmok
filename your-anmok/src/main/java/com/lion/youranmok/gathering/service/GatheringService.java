package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringPreviewDto;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final GatheringCommentRepository gatheringCommentRepository;

    public List<GatheringListDetailDto> listByCriteria() {
        return gatheringRepository.listByCriteria();
    }

    public GatheringDetailDto getDetailById(int id) {
        return gatheringRepository.getDetailById(id);
    }

    /**
     * 카테고리 페이지의 댓글 순으로 나열하기 위한 DTO 가져오는 메서드
     */
    public List<GatheringPreviewDto> getPreview() {

        List<GatheringPreviewDto> previewDtos = gatheringRepository.getPreview();
        previewDtos.forEach(preview -> {

            int cnt = gatheringCommentRepository.getCommentCnt(preview.getBoardId());
            preview.setCommentCnt(cnt);

        });

        return previewDtos;
    }

}
