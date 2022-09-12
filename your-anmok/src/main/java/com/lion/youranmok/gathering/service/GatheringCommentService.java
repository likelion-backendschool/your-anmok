package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.CommentDto;
import com.lion.youranmok.gathering.dto.CommentForm;
import com.lion.youranmok.gathering.dto.CommentMyPageDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.entity.GatheringComment;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
import com.lion.youranmok.gathering.repository.GatheringRepository;
import com.lion.youranmok.user.entity.User;
import com.lion.youranmok.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class GatheringCommentService {
    private final GatheringCommentRepository gatheringCommentRepository;

    private final UserRepository userRepository;

    public List<CommentDto> listByBoardId(int id) {
        return gatheringCommentRepository.listByBoardId(id);
    }

    public List<CommentDto> replyListByCommentId(int commentId) {
        return gatheringCommentRepository.replyListByCommentId(commentId);
    }

    public void create(GatheringBoard board, CommentForm commentForm) {
        GatheringComment gatheringComment = new GatheringComment();

        gatheringComment.setCreatedAt(LocalDateTime.now());
        gatheringComment.setModifiedAt(LocalDateTime.now());
        gatheringComment.setCommentText(commentForm.getContent());
        gatheringComment.setBoard(board);

        User user = userRepository.findByNickname(commentForm.getMentionTo());
        if(commentForm.getMentionTo() != null && user != null) {

            gatheringComment.setMentionId(user.getId());
        }
        if(commentForm.getApplyTo() != null) {
            gatheringComment.setReplyTo(commentForm.getApplyTo());
        }

        // TODO : 로그인 후 고유한 유저를 받아와서 저장해야함 지금은 랜덤유저임..
        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis()); //시드값 설정을 따로 할수도 있음
        gatheringComment.setUserId(random.nextInt(7)+1);

        gatheringCommentRepository.save(gatheringComment);
    }

    public GatheringComment findById(int id) {
        return gatheringCommentRepository.findById(id).orElse(null);
    }

    public void delete(GatheringComment gatheringComment) {
        gatheringCommentRepository.delete(gatheringComment);
    }

    public void save(GatheringComment gatheringComment) {
        gatheringCommentRepository.save(gatheringComment);
    }

    public List<CommentMyPageDto> listByUserId(int userId) {
        return gatheringCommentRepository.listByUserId(userId);
    }

}
