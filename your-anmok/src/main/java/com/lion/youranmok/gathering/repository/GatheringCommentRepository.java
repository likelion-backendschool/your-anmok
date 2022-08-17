package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.entity.GatheringComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheringCommentRepository extends JpaRepository<GatheringComment, Integer> {


}
