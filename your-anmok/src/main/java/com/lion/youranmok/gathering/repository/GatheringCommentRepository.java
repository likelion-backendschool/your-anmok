package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.CommentDto;
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
            "gc.createdAt)" +
            "from GatheringComment as gc inner join User as u on u.id = gc.userId where gc.board.id = ?1")
    List<CommentDto> listByBoardId(int id);
}
