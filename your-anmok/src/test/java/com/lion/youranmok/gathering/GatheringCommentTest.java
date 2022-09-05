package com.lion.youranmok.gathering;

import com.lion.youranmok.gathering.entity.GatheringComment;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class GatheringCommentTest {
    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private GatheringCommentRepository gatheringCommentRepository;


    @Test
    public void insertGatheringCommentTest() {
        //번개모임 댓글 세팅
        for(int i = 0; i < 20; i++) {
            GatheringComment gatheringComment = new GatheringComment();

            gatheringComment.setCommentText("%d번째 댓글".formatted(i));
            gatheringComment.setCreatedAt(LocalDateTime.now());
            gatheringComment.setModifiedAt(LocalDateTime.now());
            gatheringComment.setUserId(1);
            gatheringComment.setBoard(gatheringRepository.findById(1).orElse(null));

            gatheringCommentRepository.save(gatheringComment);
        }
    }

    @Test
    public void insertCommentReplyTest() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 3; j++) {
                GatheringComment gatheringComment = new GatheringComment();

                gatheringComment.setCommentText(String.format("대댓글 %d".formatted(j)));
                gatheringComment.setReplyTo(i+1);
                gatheringComment.setBoard(gatheringRepository.findById(1).get());
                gatheringComment.setUserId(j+1);
                gatheringComment.setModifiedAt(LocalDateTime.now());
                gatheringComment.setCreatedAt(LocalDateTime.now());
                gatheringComment.setMentionId(1);

                gatheringCommentRepository.save(gatheringComment);
            }
        }
    }
}
