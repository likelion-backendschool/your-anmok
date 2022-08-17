package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.CommentDto;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GatheringCommentService {
    private final GatheringCommentRepository gatheringCommentRepository;

    public List<CommentDto> listByBoardId(int id) {
        return gatheringCommentRepository.listByBoardId(id);
    }
}
