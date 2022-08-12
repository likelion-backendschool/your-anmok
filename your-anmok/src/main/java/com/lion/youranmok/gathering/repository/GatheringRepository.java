package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringBoard, Integer> {


    List<GatheringListDetailDto> listByCriteria();
}
