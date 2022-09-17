package com.lion.youranmok.gathering.service;

import com.lion.youranmok.gathering.dto.CommentDto;
import com.lion.youranmok.gathering.dto.CommentForm;
import com.lion.youranmok.gathering.dto.CommentMyPageDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import com.lion.youranmok.gathering.entity.GatheringComment;
import com.lion.youranmok.gathering.repository.GatheringCommentRepository;
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

    public void create(User user, GatheringBoard board, CommentForm commentForm) {
        GatheringComment gatheringComment = new GatheringComment();

        gatheringComment.setCreatedAt(LocalDateTime.now());
        gatheringComment.setModifiedAt(LocalDateTime.now());
        gatheringComment.setCommentText(commentForm.getContent());
        gatheringComment.setBoard(board);

        User commentUser = userRepository.findByNickname(commentForm.getMentionTo());
        if(commentForm.getMentionTo() != null && commentUser != null) {

            gatheringComment.setMentionId(commentUser.getId());
        }
        if(commentForm.getApplyTo() != null) {
            gatheringComment.setReplyTo(commentForm.getApplyTo());
        }
        gatheringComment.setUserId(user.getId());

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
