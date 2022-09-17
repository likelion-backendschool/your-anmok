package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.CommentDto;
import com.lion.youranmok.gathering.dto.CommentMyPageDto;
import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.entity.GatheringComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheringCommentRepository extends JpaRepository<GatheringComment, Integer> {

    @Query("select new com.lion.youranmok.gathering.dto.CommentDto (" +
            "gc.id," +
            "u.nickname," +
            "gc.commentText," +
            "u.profilePicture," +
            "gc.createdAt," +
            "u.nickname," +
            "gc.userId" +
            ") from GatheringComment as gc inner join User as u on u.id = gc.userId where gc.board.id = ?1 and gc.replyTo is null")
    List<CommentDto> listByBoardId(int id);

    @Query("select count(gc.id) from GatheringComment gc where gc.board.id = :id")
    Integer getCommentCnt(int id);

    @Query("select new com.lion.youranmok.gathering.dto.CommentDto (" +
            "gc.id," +
            "u.nickname," +
            "gc.commentText," +
            "u.profilePicture," +
            "gc.createdAt," +
            "ur.nickname," +
            "gc.userId" +
            ") from GatheringComment as gc inner join User as u on u.id = gc.userId left join User as ur on gc.mentionId = ur.id where gc.replyTo = ?1")
    List<CommentDto> replyListByCommentId(int commentId);

    @Query("select new com.lion.youranmok.gathering.dto.CommentMyPageDto (" +
            "gc.commentText," +
            "gc.createdAt," +
            "gc.board.id" +
            ") from GatheringComment as gc inner join User as u on u.id = gc.userId where gc.userId = :userId order by gc.createdAt desc")
    List<CommentMyPageDto> listByUserId(int userId);

    boolean existsByReplyTo(int commentId);
}
