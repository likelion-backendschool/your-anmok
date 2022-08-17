package com.lion.youranmok.gathering.repository;

import com.lion.youranmok.gathering.dto.GatheringDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDetailDto;
import com.lion.youranmok.gathering.dto.GatheringListDto;
import com.lion.youranmok.gathering.entity.GatheringBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheringRepository extends JpaRepository<GatheringBoard, Integer>, GatheringRepositoryCustom {


    List<GatheringListDetailDto> listByCriteria();

    @Query("select new com.lion.youranmok.gathering.dto.GatheringDetailDto (" +
            "gb.title," +
            " gb.createdAt," +
            " gb.text, " +
            "gb.totalCnt, " +
            "gb.gatherCnt, " +
            "gb.date, " +
            "p.id, " +
            "p.name, " +
            "p.address, " +
            "p.star " +
            ") from GatheringBoard as gb inner join Place as p on p.id = gb.placeId where gb.id = ?1")
    GatheringDetailDto getDetailById(int id);
}
